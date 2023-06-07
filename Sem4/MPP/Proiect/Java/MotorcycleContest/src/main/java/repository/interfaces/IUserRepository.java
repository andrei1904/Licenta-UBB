package repository.interfaces;

import domain.User;

import java.util.List;

public interface IUserRepository extends IRepository<Integer, User> {
    User FilterByUsername(String username);

}
