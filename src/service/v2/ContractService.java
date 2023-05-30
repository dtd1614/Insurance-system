package service.v2;

import repository.contract.ContractListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ContractService extends UnicastRemoteObject implements ContractServiceIF{
    private final ContractListImpl contractList;
    public ContractService(ContractListImpl contractList) throws RemoteException {
        this.contractList = contractList;
    }
}
