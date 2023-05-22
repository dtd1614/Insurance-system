package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import domain.Contract;

public interface PayServiceIF extends Remote {
	public boolean PayByCreditcard(int contractId,String creditCarditNumber,String expirationDate, String cvc) throws RemoteException;
	public String PayByVirtualAccount(int contractId) throws RemoteException;
	public ArrayList<Contract> findByCustomerId(int customerId) throws RemoteException;
	public Contract findById(int contractId) throws RemoteException;
	public boolean checkPayed(int payId);
}


