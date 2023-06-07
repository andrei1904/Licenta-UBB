package repo.file;

import domain.Client;
import domain.validators.Validator;

import java.util.List;

public class ClientFile extends AbstractFileRepository<String, Client> {
    public ClientFile(Validator<Client> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Client extractEntity(List<String> attributes) {
        Client client = new Client(attributes.get(1));
        client.setId(attributes.get(0));

        return client;
    }

    @Override
    protected String createEntityAsString(Client entity) {
        return entity.getId() + ";" + entity.getName();
    }
}
