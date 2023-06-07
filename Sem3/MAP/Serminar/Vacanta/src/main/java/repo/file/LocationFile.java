package repo.file;

import domain.Location;
import domain.validators.Validator;

import java.util.List;

public class LocationFile extends AbstractFileRepository<Double, Location> {
    public LocationFile(Validator<Location> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Location extractEntity(List<String> attributes) {
        Location location = new Location(attributes.get(1));
        location.setId(Double.parseDouble(attributes.get(0)));

        return location;
    }

    @Override
    protected String createEntityAsString(Location entity) {
        return entity.getId() + ";" + entity.getLocationName();
    }
}
