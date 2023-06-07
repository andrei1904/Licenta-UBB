package contest.domain.validators;

import contest.domain.Participant;

public class ParticipantValidator implements Validator<Participant> {
    @Override
    public void validate(Participant entity) throws ValidationException {
        if (entity.getMotorcycle() == null) {
            throw new ValidationException("Motorcycle is null!");
        }

        if (entity.getName().equals("")) {
            throw new ValidationException("Name is empty!");
        }
    }
}
