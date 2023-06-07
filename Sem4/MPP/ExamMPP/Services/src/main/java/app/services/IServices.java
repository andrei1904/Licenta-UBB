package app.services;

import app.model.User;
import app.model.UserPointsDto;
import app.model.UserWordDto;

import java.rmi.RemoteException;
import java.util.List;

public interface IServices {
    User loginUser(String username, String password, IObserver client) throws RemoteException, AppException;

    void logoutUser(String username, IObserver client) throws RemoteException, AppException;

    void startGame(User user, String word) throws RemoteException, AppException;

    List<UserWordDto> getGame() throws RemoteException, AppException;

    void checkLetter(User user, String chosenUser, String letter) throws RemoteException, AppException;

    List<UserPointsDto> getResults() throws RemoteException, AppException;
}
