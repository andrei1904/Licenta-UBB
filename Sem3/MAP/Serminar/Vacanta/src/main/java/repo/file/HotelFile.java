package repo.file;

import domain.Hotel;
import domain.Type;
import domain.validators.Validator;

import java.util.List;

public class HotelFile extends AbstractFileRepository<Double, Hotel> {
    public HotelFile(Validator<Hotel> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Hotel extractEntity(List<String> attributes) {
        Hotel hotel = new Hotel(
                Double.parseDouble(attributes.get(1)),
                attributes.get(2),
                Integer.parseInt(attributes.get(3)),
                Double.parseDouble(attributes.get(4)),
                Type.valueOf(attributes.get(5))
        );

        hotel.setId(Double.parseDouble(attributes.get(0)));
        return hotel;
    }

    @Override
    protected String createEntityAsString(Hotel entity) {
        return entity.getId() + ";" +
                entity.getLocationId() + ";" +
                entity.getHotelName() + ";" +
                entity.getNoRooms() + ";" +
                entity.getPricePerNight() + ";" +
                entity.getType();
    }
}
