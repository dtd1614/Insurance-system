package service;

import domain.Contract;
import enumeration.contract.ContractStatus;
import exception.EmptyListException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ContractServiceIF  extends Remote {
    ArrayList<Contract> getContractList(ContractStatus status) throws EmptyListException, RemoteException;

    ArrayList<Contract> getContractList(String customerId) throws RemoteException, EmptyListException;

    ArrayList<Contract> getContractList(String customerId, ContractStatus status) throws RemoteException, EmptyListException;

    Contract getContract(int contractId) throws RemoteException, NoDataException;

    int applyInsurance(Contract contract) throws RemoteException;

    boolean conclude(int id) throws NoDataException, RemoteException;

    boolean examineUnderwrite(int contractId, ContractStatus status) throws RemoteException;
}
