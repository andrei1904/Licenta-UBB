package app.services;

import app.model.User;

import java.util.List;

public interface IAppServices {
    User loginUser(String username, String password, IAppObserver player) throws AppException;

    void logoutUser(String username, IAppObserver player) throws AppException;

    boolean startGame(String username) throws AppException;
}
