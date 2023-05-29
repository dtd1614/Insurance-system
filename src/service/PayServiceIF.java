package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import domain.Contract;
import exception.EmptyListException;
import exception.NoDataException;

public interface PayServiceIF extends Remote {
	public boolean PayByCreditcard(int contractId,String creditCarditNumber,String expirationDate, String cvc) throws RemoteException, NoDataException;
	public String PayByVirtualAccount(int contractId) throws RemoteException;
	public ArrayList<Contract> getUnpaidContractList(int customerId) throws RemoteException, EmptyListException;
	public Contract getContract(int contractId) throws RemoteException, NoDataException;
	public boolean checkPayed(int payId) throws RemoteException, NoDataException;
}


