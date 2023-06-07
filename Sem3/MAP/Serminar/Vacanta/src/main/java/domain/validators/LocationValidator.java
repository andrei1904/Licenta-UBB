package domain.validators;

import domain.Location;

public class LocationValidator implements Validator<Location> {
    @Override
    public void validate(Location entity) throws ValidatorException {
        if (entity.getId() == null) {
            throw new ValidatorException("Id is null!\n");
        }

        if (entity.getLocationName() == null || entity.getLocationName().equals("")) {
            throw new ValidatorException("Location name is null!\n");
        }
    }
}
