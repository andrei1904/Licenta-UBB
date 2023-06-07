package service;

import domain.Ticket;
import repo.Repository;

import java.util.List;
import java.util.Random;

public class TicketService {
    private final Repository<Long, Ticket> repo;

    public TicketService(Repository<Long, Ticket> repo) {
        this.repo = repo;
    }

    public void addTicket(Ticket ticket) {
        Random random = new Random();
        do {
            ticket.setId((long)(random.nextInt(Integer.MAX_VALUE)));
        } while (repo.findOne(ticket.getId()).isPresent());

        repo.save(ticket);
    }

    public List<Ticket> getAll() {
        return repo.findAll();
    }
}
