package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MakePolicyService extends UnicastRemoteObject implements MakePolicyServiceIF {
    protected MakePolicyService() throws RemoteException {
    }
}
