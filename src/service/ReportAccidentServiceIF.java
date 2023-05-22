package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import domain.Accident;
import domain.Contract;
import exception.EmptyListException;

public interface ReportAccidentServiceIF extends Remote {
	public ArrayList<Contract> findByCustomerId(int customerId) throws RemoteException,EmptyListException;
	public Contract findById(int contractId) throws RemoteException, EmptyListException;
	public boolean reportAccident(Accident accident) throws RemoteException;
}
