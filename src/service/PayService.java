package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import domain.Contract;
import domain.Pay;
import enumeration.pay.PayStatus;
import enumeration.pay.PaymentMethod;
import exception.EmptyListException;
import repository.contract.ContractListImpl;
import repository.customer.CustomerListImpl;
import repository.pay.PayListImpl;

public class PayService extends UnicastRemoteObject implements PayServiceIF {
	private final ContractListImpl contractList;
	private final CustomerListImpl customerList;
	private final PayListImpl payList;
    public PayService(ContractListImpl contractList,CustomerListImpl customerList,PayListImpl payList) throws RemoteException {
    	this.contractList = contractList;
    	this.customerList = customerList;
		this.payList = payList;
    }
	@Override
	public String PayByVirtualAccount(int contractId) throws RemoteException {
		Random random = new Random();
		String accountNumber = "";
		int randomNumber;
		for(int i=0;i<10;i++) {
			randomNumber=random.nextInt();
			accountNumber=accountNumber+Integer.toString(randomNumber);
		}
		Pay pay = new Pay(contractId, PaymentMethod.Account, Integer.parseInt(accountNumber), -1, PayStatus.NonPayment);
		this.payList.add(pay);
		return accountNumber;
	}


	@Override
	public ArrayList<Contract> findByCustomerId(int customerId) throws RemoteException,EmptyListException {
		return this.contractList.findByCustomerId(customerId);
	}




	@Override
	public Contract findById(int contractId) throws RemoteException,EmptyListException {
		return this.contractList.finByContractId(contractId);
	}
	@Override
	public boolean PayByCreditcard(int contractId,String creditCarditNumber, String expirationDate, String cvc) throws RemoteException {
		Pay pay = new Pay(contractId, PaymentMethod.Card, -1,Integer.parseInt(creditCarditNumber), PayStatus.NonPayment);
		this.payList.add(pay);
		//결제 진행
//		if(true)
//			pay.payed();
//		return true;
//		else
//		return false;
		return true;
	}
	@Override
	public boolean checkPayed(int payId) {
		Pay pay=this.payList.findById(payId);
		
		return pay.payed();
	}
}
