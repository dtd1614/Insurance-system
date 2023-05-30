package service;

import repository.CompensationList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CompensateService extends UnicastRemoteObject implements CompensateServiceIF {
    private final CompensationList compensationList;
    public CompensateService(CompensationList compensationList) throws RemoteException {
        this.compensationList = compensationList;
    }
}
