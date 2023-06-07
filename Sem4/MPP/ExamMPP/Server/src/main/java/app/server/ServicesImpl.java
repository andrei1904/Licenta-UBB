package app.server;

import app.model.Move;
import app.model.User;
import app.model.UserPointsDto;
import app.model.UserWordDto;
import app.persistence.MoveRepository;
import app.persistence.UserRepository;
import app.services.AppException;
import app.services.IObserver;
import app.services.IServices;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicesImpl implements IServices {
    private final UserRepository userRepository;
    private final MoveRepository moveRepository;

    private final Map<String, IObserver> loggedUsers;
    private final Map<String, String> words;
    private final Map<String, String> hiddenWords;
    private final List<String> usersThisRound;

    private Integer game;
    private Integer round;

    public ServicesImpl(UserRepository userRepository, MoveRepository moveRepository) {
        this.userRepository = userRepository;
        this.moveRepository = moveRepository;
        loggedUsers = new ConcurrentHashMap<>();
        words = new ConcurrentHashMap<>();
        hiddenWords = new ConcurrentHashMap<>();
        usersThisRound = new ArrayList<>();

        round = 0;
//
//        UserWordDto[] res = moveRepository.getChosenWordsForGame(0);
//        System.out.println(res[0]);

        List<Move> moves = (List<Move>) moveRepository.findAll();
        int maxi = -1;
        for (Move m : moves) {
            if (m.getGameId() > maxi) {
                maxi = m.getGameId();
            }
        }
        game = maxi + 1;
    }

    @Override
    public User loginUser(String username, String password, IObserver client) throws RemoteException, AppException {
        User user = userRepository.findById(username);

        if (loggedUsers.size() >= 3) {
            throw new AppException("Already 3 users logged in!");
        }

        if (user != null && user.getPassword().equals(password)) {
            if (loggedUsers.get(user.getUsername()) != null) {
                throw new AppException("User already logged in!");
            }

            loggedUsers.put(user.getUsername(), client);
            if (loggedUsers.size() == 3) {
                notifyGameStart();
            }
            return user;
        } else {
            throw new AppException("Authentification failed!");
        }
    }

    @Override
    public void logoutUser(String username, IObserver client) throws RemoteException, AppException {
        if (!username.equals("")) {
            loggedUsers.remove(username);
        }
    }

    @Override
    public void startGame(User user, String word) throws RemoteException, AppException {
        if (words.get(user.getUsername()) != null) {
            throw new AppException("Game already started!");
        }

        words.put(user.getUsername(), word);

        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            hiddenWord += "_";
        }
        hiddenWords.put(user.getUsername(), hiddenWord);

        moveRepository.add(new Move(game, round, user, word, "", "", 0));

        usersThisRound.add(user.getUsername());
        if (usersThisRound.size() == 3) {
            usersThisRound.clear();
            notifyRoundDone();
            round += 1;
        }

    }

    @Override
    public List<UserWordDto> getGame() throws RemoteException, AppException {
        List<UserWordDto> res = new ArrayList<>();
        for (String username : hiddenWords.keySet()) {
            res.add(new UserWordDto(username, hiddenWords.get(username)));
        }

        return res;
    }

    @Override
    public void checkLetter(User user, String chosenUser, String letter) throws RemoteException, AppException {
        if (round == 0) {
            throw new AppException("Wait for other players to start!");
        }

        if (usersThisRound.contains(user.getUsername())) {
            throw new AppException("You sent a letter this round!");
        }

        usersThisRound.add(user.getUsername());
        int points = 0;
        String chosenWord = words.get(chosenUser);
        String hiddenWord = hiddenWords.get(chosenUser);

        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == letter.charAt(0)) {
                points += 1;

                char[] text = hiddenWord.toCharArray();
                text[i] = letter.charAt(0);
                hiddenWord = String.valueOf(text);
                hiddenWords.put(chosenUser, String.valueOf(text));
            }
        }
        moveRepository.add(new Move(game, round, user, words.get(user.getUsername()), letter, chosenUser, points));

        if (usersThisRound.size() == 3) {
            usersThisRound.clear();
            round += 1;
            notifyRoundDone();
            if (round == 4) {
                notifyGameEnded();
            }
        }

    }

    @Override
    public List<UserPointsDto> getResults() throws RemoteException, AppException {
        List<UserPointsDto> results = new ArrayList<>();


        for (String user : words.keySet()) {
            int points = 0;
            List<Move> moves = (List<Move>) moveRepository.findForGame(game);
            for (Move move : moves) {
                if (move.getUser().getUsername().equals(user)) {
                    points += move.getPoints();
                }
            }
            results.add(new UserPointsDto(user, points));
        }

        results.sort(Comparator.comparingInt(UserPointsDto::getPoints));
        return results;
    }

    public void notifyGameStart() {
        int defaultThreadsNo = 5;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);

        for (IObserver client : loggedUsers.values()) {
            if (client != null) {
                executor.execute(() -> {
                    try {
                        client.canStart();
                    } catch (AppException | RemoteException e) {
                        System.err.println("Error notifying clients " + e);
                    }
                });
            }
        }
        executor.shutdown();
    }

    public void notifyRoundDone() {
        int defaultThreadsNo = 5;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);

        for (IObserver client : loggedUsers.values()) {
            if (client != null) {
                executor.execute(() -> {
                    try {
                        client.roundDone();
                    } catch (AppException | RemoteException e) {
                        System.err.println("Error notifying clients " + e);
                    }
                });
            }
        }
        executor.shutdown();
    }

    public void notifyGameEnded() {
        int defaultThreadsNo = 5;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);

        for (IObserver client : loggedUsers.values()) {
            if (client != null) {
                executor.execute(() -> {
                    try {
                        client.gameEnded();
                    } catch (AppException | RemoteException e) {
                        System.err.println("Error notifying clients " + e);
                    }
                });
            }
        }
        executor.shutdown();
    }
}
