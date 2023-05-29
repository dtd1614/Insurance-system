package service.v2;

import repository.compensation.CompensationListImpl;
import service.CompensateServiceIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CompensateService extends UnicastRemoteObject implements CompensateServiceIF {
    private final CompensationListImpl compensationList;
    public CompensateService(CompensationListImpl compensationList) throws RemoteException {
        this.compensationList = compensationList;
    }
}
