package domain.validators;

import domain.Flight;

public class FlightValidator implements Validator<Flight> {
    @Override
    public void validate(Flight entity) throws ValidatorException {
        if (entity.getId() == null || entity.getId() < 0) {
            throw new ValidatorException("Id is invalid!\n");
        }

        if (entity.getFrom() == null) {
            throw new ValidatorException("Location from is invalid!\n");
        }

        if (entity.getTo() == null) {
            throw new ValidatorException("Location to is invalid!\n");
        }

        if (entity.getDepartureTime() == null) {
            throw new ValidatorException("Departure time is invalid!\n");
        }

        if (entity.getLandingTime() == null) {
            throw new ValidatorException("Landing time is invalid!\n");
        }

        if (entity.getSeats() < 0) {
            throw new ValidatorException("Seats are invalid!\n");
        }

        if (entity.getDepartureTime().compareTo(entity.getLandingTime()) > 0) {
            throw new ValidatorException("Dates are invalid!\n");
        }
    }
}
