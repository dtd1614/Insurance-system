package service;

import domain.Contract;
import domain.Info.Info;
import domain.customer.Customer;
import exception.EmptyListException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UnderwriteServiceIF extends Remote {
    ArrayList<Contract> getApplyContractList() throws RemoteException, EmptyListException;

    Customer getCustomer(String customerId) throws RemoteException, NoDataException;

    Info getInfo(int infoId) throws RemoteException, NoDataException;
    boolean Underwrite(int contractId) throws RemoteException;
}
