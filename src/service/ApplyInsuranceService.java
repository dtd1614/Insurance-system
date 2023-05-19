package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ApplyInsuranceService extends UnicastRemoteObject implements ApplyInsuranceServiceIF {
    public ApplyInsuranceService() throws RemoteException {
    }
}
