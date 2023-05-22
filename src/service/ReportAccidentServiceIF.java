package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import domain.Accident;
import domain.Contract;

public interface ReportAccidentServiceIF extends Remote {
	public ArrayList<Contract> findByCustomerId(int customerId) throws RemoteException;
	public Contract findById(int contractId) throws RemoteException;
	public boolean reportAccident(Accident accident) throws RemoteException;
}
