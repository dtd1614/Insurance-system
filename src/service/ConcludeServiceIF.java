package service;

import domain.Contract;
import domain.Insurance;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConcludeServiceIF extends Remote {
//
//    int Searchapplicationlist();
//
    boolean conclude(int selectedId);

    Contract getContract(int selectedContractId) throws RemoteException, NoDataException;

    Insurance getInsurance(int selectedInsuranceId) throws RemoteException, NoDataException;
}
