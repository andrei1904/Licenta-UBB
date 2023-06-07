package domain.validators;

import domain.User;

public class UserValidator implements Validator<User>{
    @Override
    public void validate(User entity) throws ValidationException {
        if (entity.getUsername().equals("")) {
            throw new ValidationException("Username is invalid!");
        }
    }
}
