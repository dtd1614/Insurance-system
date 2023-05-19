package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UnderwriteService extends UnicastRemoteObject implements UnderwriteServiceIF {
    public UnderwriteService() throws RemoteException {
    }
}
