package service;

import domain.Contract;
import domain.Info.CustomerInfo;
import enumeration.contract.ContractStatus;
import exception.EmptyListException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ContractServiceIF  extends Remote {
    ArrayList<Contract> getContractList(ContractStatus status) throws EmptyListException, RemoteException;

    ArrayList<Contract> getContractList(String customerId) throws RemoteException, EmptyListException;

    ArrayList<Contract> getContractList(String customerId, ContractStatus status) throws RemoteException, EmptyListException;

    Contract getContract(int contractId) throws RemoteException, NoDataException;

    int applyInsurance(Contract contract, CustomerInfo customerInfo) throws RemoteException;

    boolean conclude(int id) throws NoDataException, RemoteException;

    boolean examineUnderwrite(int contractId, ContractStatus status) throws RemoteException;

    ArrayList<Contract> getUnpaidContractList(String customerId) throws RemoteException, EmptyListException;

    boolean setPaymentDeadline(int id, Timestamp valueOf) throws RemoteException;
}
