package domain.validators;

import domain.Participant;

public class ParticipantValidator implements Validator<Participant> {
    @Override
    public void validate(Participant entity) throws ValidationException {
        if (entity.getMotorcycle() == null) {
            throw new ValidationException("Motorcycle is null!");
        }
    }
}
