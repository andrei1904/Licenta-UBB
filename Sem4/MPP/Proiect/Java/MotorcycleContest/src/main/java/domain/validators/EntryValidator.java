package domain.validators;

import domain.Entry;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;

public class EntryValidator implements Validator<Entry> {

    @Override
    public void validate(Entry entity) throws ValidationException {
        if (entity.getParticipantId() < 0) {
            throw new ValidationException("ParticipantId is invalid");
        }
    }
}
