package service;

import domain.Client;
import domain.Flight;
import domain.Ticket;
import utils.events.ChangeEvent;
import utils.observer.Observable;
import utils.observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AllService {
    ClientService clientService;
    FlightService flightService;
    TicketService ticketService;

    public static class FlightObservable implements Observable<ChangeEvent<Flight>> {

        private final List<Observer<ChangeEvent<Flight>>> flightObservers = new ArrayList<>();

        @Override
        public void addObserver(Observer<ChangeEvent<Flight>> e) {
            flightObservers.add(e);
        }

        @Override
        public void removeObserver(Observer<ChangeEvent<Flight>> e) {
            flightObservers.remove(e);
        }

        @Override
        public void notifyObservers() {
            flightObservers.forEach(Observer::update);
        }
    }

    private final FlightObservable flightObservable = new FlightObservable();

    public void addFlightObserver(Observer<ChangeEvent<Flight>> o) {
        flightObservable.addObserver(o);
    }



    public AllService(ClientService clientService, FlightService flightService, TicketService ticketService) {
        this.clientService = clientService;
        this.flightService = flightService;
        this.ticketService = ticketService;
    }

    public boolean existingUser(String inputUsername) {
        for (Client client : clientService.getAll()) {
            if (client.getId().equals(inputUsername)) {
                return true;
            }
        }
        return false;
    }

    public Client getClient(String username) {
        return clientService.getOne(username);
    }

    public List<Flight> getAllFlights() {
        return flightService.getAll();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<String> getFromDestinations() {
        return flightService.getAll()
                .stream()
                .filter(distinctByKey(Flight::getFrom))
                .map(Flight::getFrom)
                .collect(Collectors.toList());
    }

    public List<String> getToDestinations() {
        return flightService.getAll()
                .stream()
                .filter(distinctByKey(Flight::getTo))
                .map(Flight::getTo)
                .collect(Collectors.toList());
    }

    public List<Flight> filterFlights(String from, String to, LocalDate date) {
        return flightService.getAll()
                .stream()
                .filter(x -> x.getFrom().equals(from))
                .filter(x -> x.getTo().equals(to))
                .filter(x -> x.getDepartureTime().getYear() == date.getYear() &&
                        x.getDepartureTime().getMonth() == date.getMonth() &&
                        x.getDepartureTime().getDayOfMonth() == date.getDayOfMonth())
                .collect(Collectors.toList());
    }

    public void addPurchase(Client client, Flight flight) {
        ticketService.addTicket(new Ticket(client.getId(), flight.getId(), LocalDateTime.now()));
        flightObservable.notifyObservers();
    }

    public long getNumberOfTickets(Long flightId) {
        return ticketService.getAll()
                .stream()
                .filter(x -> x.getFlightId().equals(flightId))
                .count();
    }

    public void setAvailableSeats() {
        List<Flight> flights = flightService.getAll();

        for (Flight flight : flights) {
            flight.setAvailableSeats((int) (flight.getSeats() - getNumberOfTickets(flight.getId())));
        }

    }

}
