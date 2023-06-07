package repo.file;

import domain.Flight;
import domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFile extends AbstractFileRepository<Long, Flight> {
    public FlightFile(Validator<Flight> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Flight extractEntity(List<String> attributes) {
        Flight flight = new Flight(
                attributes.get(1),
                attributes.get(2),
                LocalDateTime.parse(attributes.get(3)),
                LocalDateTime.parse(attributes.get(4)),
                Integer.parseInt(attributes.get(5)),
                Integer.parseInt(attributes.get(6))
        );
        flight.setId(Long.parseLong(attributes.get(0)));

        return flight;
    }

    @Override
    protected String createEntityAsString(Flight entity) {
        return entity.getId() + ";" + entity.getFrom() + ";" + entity.getTo() + ";"
                + entity.getDepartureTime() + ";" + entity.getLandingTime() + ";"
                + entity.getSeats() + entity.getAvailableSeats();
    }
}
