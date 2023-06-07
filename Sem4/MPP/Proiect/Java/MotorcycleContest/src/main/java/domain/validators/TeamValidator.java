package domain.validators;

import domain.Team;

public class TeamValidator implements Validator<Team> {
    @Override
    public void validate(Team entity) throws ValidationException {
        if (entity.getName().equals("")) {
            throw new ValidationException("Name is invalid!");
        }
    }
}
