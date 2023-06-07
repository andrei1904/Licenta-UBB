package socialnetwork.service;

import socialnetwork.domain.*;
import socialnetwork.repository.pdf.ActivityPdf;
import socialnetwork.repository.pdf.MessagesPdf;
import socialnetwork.utils.events.ChangeEvent;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AllService {
    private final UtilizatorService utilizatorService;
    private final CererePrietenieService cererePrietenieService;
    private final PrieteniiService prieteniiService;
    private final MessageService messageService;

    public AllService(UtilizatorService utilizatorService, CererePrietenieService cererePrietenieService, PrieteniiService prieteniiService, MessageService messageService) {
        this.utilizatorService = utilizatorService;
        this.cererePrietenieService = cererePrietenieService;
        this.prieteniiService = prieteniiService;
        this.messageService = messageService;
    }

    public static class PrietenieObservable implements Observable<ChangeEvent<Prietenie>> {

        private final List<Observer<ChangeEvent<Prietenie>>> prietenieObservers = new ArrayList<>();

        @Override
        public void addObserver(Observer<ChangeEvent<Prietenie>> e) {
            prietenieObservers.add(e);
        }

        @Override
        public void removeObserver(Observer<ChangeEvent<Prietenie>> e) {
            prietenieObservers.remove(e);
        }

        @Override
        public void notifyObservers() {
            prietenieObservers.forEach(Observer::update);
        }
    }

    private final PrietenieObservable prietenieObservable = new PrietenieObservable();

    public void addPrietenieObserver(Observer<ChangeEvent<Prietenie>> o) {
        prietenieObservable.addObserver(o);
    }


    public void deletePrietenie(int id1, int id2) {
        prieteniiService.deletePrietenie(id1, id2);
        cererePrietenieService.deleteCerere(id1, id2);
        prietenieObservable.notifyObservers();
    }

    public void deleteCerere(int id1, int id2) {
        cererePrietenieService.deleteCerere(id1, id2);
        prietenieObservable.notifyObservers();
    }

    public void addPrietenie(int id1, int id2) {
        cererePrietenieService.sendFriendRequest(id1, id2);
        prietenieObservable.notifyObservers();
    }

    public void acceptPrietenie(int id) {
        cererePrietenieService.confirmFriendRequest(id, 1);
        prietenieObservable.notifyObservers();
    }

    public void declinePrietenie(int id) {
        cererePrietenieService.confirmFriendRequest(id, 0);
        prietenieObservable.notifyObservers();
    }


    public Iterable<Utilizator> getAllUsers() {
        return utilizatorService.getAll();
    }

    public boolean existaUser(int id) {
        return utilizatorService.existaUtilizator(id);
    }

    public Utilizator getOneUser(int id) {
        return utilizatorService.getOne(id);
    }

    public List<DtoCererePrietenie> getRecivedForUser(int id) {
        return cererePrietenieService.getRecivedForUser(id);
    }

    public List<DtoCererePrietenie> getSentForUser(int id) {
        return cererePrietenieService.getSentForUser(id);
    }

    public List<DtoPrieten> getPrieteniUser(int id) {
        return prieteniiService.prieteniUtilizator(id);
    }

    public List<Message> getMessages(int id1, int id2) {
        return messageService.showMessages(id1, id2);
    }

    public void sendMessageToOne(int id1, int id2, String text) {
        List<Message> mesaje = getMessages(id1, id2);
        if (mesaje != null) {
            messageService.replyMessage(id1, id2, mesaje.get(mesaje.size() - 1).getId(), text);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(id2);
            messageService.sendMessage(id1, list, text);
        }
        prietenieObservable.notifyObservers();
    }

    public void sendMessageToMore(int id1, List<Integer> to, String text) {
        List<Integer> list = messageService.getSpecialUsers(id1, to);

        for (Integer i : list) {
            to.remove(i);
        }

        if (!to.isEmpty()) {
            messageService.sendMessage(id1, to, text);
        }

        for (Integer i : list) {
            List<Message> mesaje = getMessages(id1, i);
            if (mesaje != null) {
                messageService.replyMessage(id1, i, mesaje.get(mesaje.size() - 1).getId(), text);
            }
        }

    }

    public void generatePdfFriendsMessages(LocalDate from, LocalDate to,
                                           Utilizator user, File selectedDirectory) {
        List<Prietenie> prietenii = prieteniiService.getPrieteniiUtilizator(user);

        List<Utilizator> rezPrietenii = prietenii.stream()
                .filter(x -> x.getDate().toLocalDate().compareTo(from) >= 0
                            && x.getDate().toLocalDate().compareTo(to) <= 0)
                .map(x -> {
                    if (x.getIdPrieten1() == user.getId()) {
                        return utilizatorService.getOne(x.getIdPrieten2());
                    } else {
                        return utilizatorService.getOne(x.getIdPrieten1());
                    }
                })
                .collect(Collectors.toList());

        List<Message> messages = messageService.getUserMessages(user);

        List<Message> rezMessages = messages.stream()
                .filter(x -> x.getDate().toLocalDate().compareTo(from) >= 0
                        && x.getDate().toLocalDate().compareTo(to) <= 0)
                .sorted(Comparator.comparing(Message::getDate))
                .collect(Collectors.toList());

        ActivityPdf activityPdf = new ActivityPdf(user);
        activityPdf.generate(selectedDirectory, "\\ActivityReport.pdf", from, to,
                rezPrietenii, rezMessages);
    }

    public void generatePdfMessages(LocalDate from, LocalDate to,
                                    Utilizator user, int id, File selectedDirectory) {
        List<Message> messages = messageService.getUserMessages(user);

        List<Message> rezMessages = messages.stream()
                .filter(x -> x.getDate().toLocalDate().compareTo(from) >= 0
                        && x.getDate().toLocalDate().compareTo(to) <= 0)
                .filter(x -> x.getFrom().getId() == id)
                .sorted(Comparator.comparing(Message::getDate))
                .collect(Collectors.toList());

        Utilizator friend = utilizatorService.getOne(id);
        List<Utilizator> friends = new ArrayList<>();
        friends.add(friend);

        MessagesPdf messagesPdf = new MessagesPdf(user);
        messagesPdf.generate(selectedDirectory, "\\MessagesReport.pdf", from, to,
                friends, rezMessages);
    }
}
