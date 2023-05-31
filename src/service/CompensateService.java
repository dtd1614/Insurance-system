package service;

import dao.CompensationDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CompensateService extends UnicastRemoteObject implements CompensateServiceIF {
    private final CompensationDao compensationDao;
    public CompensateService(CompensationDao compensationDao) throws RemoteException {
        this.compensationDao = compensationDao;
    }
}
