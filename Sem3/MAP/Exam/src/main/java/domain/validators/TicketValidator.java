package domain.validators;

import domain.Ticket;

public class TicketValidator implements Validator<Ticket> {
    @Override
    public void validate(Ticket entity) throws ValidatorException {
        if (entity.getId() == null) {
            throw new ValidatorException("Id is invalid!\n");
        }

        if (entity.getUsername() == null || entity.getUsername().equals("")) {
            throw new ValidatorException("Username is invalid!\n");
        }

        if (entity.getFlightId() == null || entity.getFlightId() < 0) {
            throw new ValidatorException("FlightId is invalid!\n");
        }

        if (entity.getPurchaseTime() == null) {
            throw new ValidatorException("Purchase time is invalid!\n");
        }
    }
}
