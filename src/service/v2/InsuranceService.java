package service.v2;

import repository.insurance.InsuranceListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InsuranceService extends UnicastRemoteObject implements InsuranceServiceIF{
    private final InsuranceListImpl insuranceList;
    public InsuranceService(InsuranceListImpl insuranceList) throws RemoteException {
        this.insuranceList = insuranceList;
    }
}
