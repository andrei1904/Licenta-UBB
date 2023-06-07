package app.persistence.interfaces;

import app.model.User;

public interface IUserRepository extends IRepository<String, User> {
    public User getOneByUsername(String username);
}
