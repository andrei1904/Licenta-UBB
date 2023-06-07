package contest.domain.validators;


import contest.domain.Race;

public class RaceValidator implements Validator<Race> {
    @Override
    public void validate(Race entity) throws ValidationException {
        if (entity.getName().equals("")) {
            throw new ValidationException("Name is invalid!");
        }
    }
}
