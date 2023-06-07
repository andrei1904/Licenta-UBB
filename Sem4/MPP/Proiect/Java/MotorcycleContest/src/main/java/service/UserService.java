package service;

import domain.User;
import repository.UserDbRepository;

public class UserService {
    private final UserDbRepository repo;

    public UserService(UserDbRepository repo) {
        this.repo = repo;
    }

    public User getUser(String username) {
        return repo.FilterByUsername(username);
    }
}
