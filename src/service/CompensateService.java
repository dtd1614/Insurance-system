package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CompensateService extends UnicastRemoteObject implements CompensateServiceIF {
    public CompensateService() throws RemoteException {
    }
}
