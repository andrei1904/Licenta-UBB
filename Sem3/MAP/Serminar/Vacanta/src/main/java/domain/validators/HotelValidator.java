package domain.validators;

import domain.Hotel;

public class HotelValidator implements Validator<Hotel> {
    @Override
    public void validate(Hotel entity) throws ValidatorException {
        if (entity.getId() == null || entity.getId() < 0) {
            throw new ValidatorException("Id is null!\n");
        }

        if (entity.getHotelName() == null || entity.getHotelName().equals("")) {
            throw new ValidatorException("Hotel name is null!\n");
        }

        if (entity.getLocationId() < 0) {
            throw new ValidatorException("Location Id is invalid!\n");
        }

        if (entity.getNoRooms() < 1) {
            throw new ValidatorException("No of rooms is invalid!\n");
        }
    }
}
