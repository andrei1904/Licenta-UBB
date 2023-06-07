package domain.validators;

import domain.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        if (entity.getId() == null || entity.getId().equals("")) {
            throw new ValidatorException("Id is invalid!\n");
        }

        if (entity.getName() == null || entity.getName().equals("")) {
            throw new ValidatorException("Name is invalid!\n");
        }
    }
}
