package app.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAppObserver extends Remote {
    void gameStarted(List<String> participants) throws AppException, RemoteException;
}
