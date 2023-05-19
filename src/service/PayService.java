package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PayService extends UnicastRemoteObject implements PayServiceIF {
    public PayService() throws RemoteException {

    }
}
