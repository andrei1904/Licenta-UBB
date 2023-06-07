package contest.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IContestObserver extends Remote {
    void participantEntryAdded(String raceName) throws ContestException, RemoteException;
}
