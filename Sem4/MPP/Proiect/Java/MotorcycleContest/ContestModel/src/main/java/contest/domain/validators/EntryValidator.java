package contest.domain.validators;


import contest.domain.Entry;

public class EntryValidator implements Validator<Entry> {

    @Override
    public void validate(Entry entity) throws ValidationException {
        if (entity.getParticipantId() < 0) {
            throw new ValidationException("ParticipantId is invalid");
        }

    }
}
