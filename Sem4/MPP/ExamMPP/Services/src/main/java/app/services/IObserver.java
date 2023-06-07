package app.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
    void canStart() throws RemoteException, AppException;

    void roundDone() throws RemoteException, AppException;

    void gameEnded() throws RemoteException, AppException;
}
