package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import domain.Contract;
import domain.Pay;
import enumeration.contract.PaymentCycle;
import enumeration.pay.PayStatus;
import enumeration.pay.PaymentMethod;
import exception.EmptyListException;
import exception.NoDataException;
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
	public ArrayList<Contract> getUnpaidContractList(int customerId) throws RemoteException,EmptyListException {
		if(this.contractList.getUnpaidContractList(customerId,new Timestamp(System.currentTimeMillis())).size()==0)
			throw new EmptyListException("돈을 지불해야 할 계약이 존재하지 않습니다.");
		else
		return this.contractList.getUnpaidContractList(customerId, new Timestamp(System.currentTimeMillis()));
	}
	
	@Override
	public Contract getContract(int contractId) throws RemoteException,NoDataException {
		if(this.contractList.getContract(contractId)==null) {
			new NoDataException("해당 계약이 없습니다.");
		}
			return this.contractList.getContract(contractId);
	}
	
	@Override
	public boolean PayByCreditcard(int contractId,String creditCarditNumber, String expirationDate, String cvc) throws RemoteException, NoDataException {
		Pay pay = new Pay(contractId, PaymentMethod.Card, -1,Integer.parseInt(creditCarditNumber), PayStatus.NonPayment);
		this.payList.add(pay);

		Contract paiedContract= getContract(pay.getContractId());
		if(paiedContract==null) {
			new NoDataException("해당 계약이 없습니다.");
		}
		if(paiedContract.paied())
			return pay.paied();
			else 
			return false;
	}
	
	@Override
	public boolean checkPayed(int payId) throws RemoteException, NoDataException {
		Pay pay=this.payList.findById(payId);
		Contract paiedContract= getContract(pay.getContractId());
		if(paiedContract==null) {
			new NoDataException("해당 계약이 없습니다.");
		}
		if(paiedContract.paied())
		return pay.paied();
		else
		return false;
	}
}