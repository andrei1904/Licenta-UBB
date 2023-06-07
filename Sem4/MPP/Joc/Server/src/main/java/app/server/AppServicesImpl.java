package app.server;

import app.model.User;
import app.persistence.UserRepository;
import app.persistence.WordRepository;
import app.services.AppException;
import app.services.IAppObserver;
import app.services.IAppServices;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServicesImpl implements IAppServices {
    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    private final Map<String, IAppObserver> loggedUsers;
    private final Queue<String> queue;

    public AppServicesImpl(UserRepository userRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        loggedUsers = new ConcurrentHashMap<>();
        queue = new LinkedList<>();
    }

    @Override
    public synchronized User loginUser(String username, String password, IAppObserver player) throws AppException {
        User user = userRepository.getOneByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if (loggedUsers.get(user.getUsername()) != null) {
                throw new AppException("User already logged in!");
            }

            loggedUsers.put(user.getUsername(), player);

            return user;
        } else {
            throw new AppException("Authentification failed!");
        }
    }

    @Override
    public synchronized void logoutUser(String username, IAppObserver player) throws AppException {
        loggedUsers.remove(username);
        queue.remove(username);
    }

    @Override
    public synchronized boolean startGame(String username) throws AppException {
        queue.add(username);

        if (queue.size() < 3) {
            return false;
        } else {
            List<String> res = new ArrayList<>(queue);
            queue.clear();
            notifyGameStarted(res);

            return true;
        }
    }



    private synchronized void notifyGameStarted(List<String> participants) {
        int defaultThreadsNo = 5;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);

        for (IAppObserver user : loggedUsers.values()) {
            if (user != null) {
                executor.execute(() -> {
                    try {
                        user.gameStarted(participants);
                    } catch (AppException | RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executor.shutdown();
    }
}
