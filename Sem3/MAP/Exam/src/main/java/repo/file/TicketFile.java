package repo.file;

import domain.Ticket;
import domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class TicketFile extends AbstractFileRepository<Long, Ticket> {
    public TicketFile(Validator<Ticket> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Ticket extractEntity(List<String> attributes) {
        Ticket ticket = new Ticket(
                attributes.get(1),
                Long.parseLong(attributes.get(2)),
                LocalDateTime.parse(attributes.get(3))
        );
        ticket.setId(Long.parseLong(attributes.get(0)));
        return ticket;
    }

    @Override
    protected String createEntityAsString(Ticket entity) {
        return entity.getId() + ";" + entity.getUsername() + ";" + entity.getFlightId()
                + ";" + entity.getPurchaseTime();
    }
}
