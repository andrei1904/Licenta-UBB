package domain.validators;

import domain.Motorcycle;

public class MotorcycleValidator implements Validator<Motorcycle> {
    @Override
    public void validate(Motorcycle entity) throws ValidationException {
//        if (entity.getId() < 0) {
//            throw new ValidationException("Id is invalid!");
//        }
    }
}
