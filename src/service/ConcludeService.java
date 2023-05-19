package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConcludeService extends UnicastRemoteObject implements ConcludeServiceIF {
    public ConcludeService() throws RemoteException {
    }

}
