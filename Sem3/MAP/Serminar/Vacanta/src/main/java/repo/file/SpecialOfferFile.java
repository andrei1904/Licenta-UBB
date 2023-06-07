package repo.file;

import domain.SpecialOffer;
import domain.validators.Validator;

import java.time.LocalDate;
import java.util.List;

public class SpecialOfferFile extends AbstractFileRepository<Double, SpecialOffer> {
    public SpecialOfferFile(Validator<SpecialOffer> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public SpecialOffer extractEntity(List<String> attributes) {
        SpecialOffer specialOffer = new SpecialOffer(
                Double.parseDouble(attributes.get(1)),
                LocalDate.parse(attributes.get(2)),
                LocalDate.parse(attributes.get(3)),
                Integer.parseInt(attributes.get(4))
        );
        specialOffer.setId(Double.parseDouble(attributes.get(0)));

        return specialOffer;
    }

    @Override
    protected String createEntityAsString(SpecialOffer entity) {
        return entity.getId() + ";" +
                entity.getHotelId() + ";" +
                entity.getStartDate() + ";" +
                entity.getEndDate() + ";" +
                entity.getPercents();
    }
}
