package service;

import domain.Client;
import repo.Repository;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final Repository<String, Client> repo;


    public ClientService(Repository<String, Client> repo) {
        this.repo = repo;
    }

    public List<Client> getAll() {
        return repo.findAll();
    }

    public Client getOne(String username) {
        Optional<Client> res = repo.findOne(username);
        return res.orElse(null);
    }
}
