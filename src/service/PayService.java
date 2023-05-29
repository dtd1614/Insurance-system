package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import domain.Contract;
import domain.Pay;
import enumeration.contract.ContractStatus;
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
	private final PayListImpl payList;
    public PayService(ContractListImpl contractList,PayListImpl payList) throws RemoteException {
    	this.contractList = contractList;
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
		ArrayList<Contract> unpaidContractList=this.contractList.findByCustomerId(customerId);
		if(unpaidContractList.size()==0)
			throw new EmptyListException("가입한 계약이 존재하지 않습니다.");
		else {
			Timestamp now=new Timestamp(System.currentTimeMillis());
			for(Contract contract:unpaidContractList) {
				Timestamp deadlineStamp=contract.getPaymentDeadline();
				LocalDateTime deadline = deadlineStamp.toLocalDateTime();
		        LocalDateTime nowTime = now.toLocalDateTime();
				long daysDifference = ChronoUnit.DAYS.between(nowTime,deadline);
				if(!contract.getStatus().equals(ContractStatus.Conclude)||daysDifference>=7) {
					unpaidContractList.remove(contract);
				}
			}
			if(unpaidContractList.size()==0)
				throw new EmptyListException("돈을 지불해야 할 계약이 존재하지 않습니다.");
			return unpaidContractList;
		}
			
	}
	
	@Override
	public Contract getContract(int contractId) throws RemoteException,NoDataException {
		Contract contract=this.contractList.findByContractId(contractId);
		if(contract==null) {
			new NoDataException("해당 계약이 없습니다.");
		}
			return contract;
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
		Pay pay=this.payList.findByPayId(payId);
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