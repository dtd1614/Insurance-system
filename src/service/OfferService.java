package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OfferService extends UnicastRemoteObject implements OfferServiceIF {
    protected OfferService() throws RemoteException {
    }
}
