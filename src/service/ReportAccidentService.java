package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import domain.Accident;
import domain.Contract;
import exception.EmptyListException;
import exception.NoDataException;
import repository.accident.AccidentListImpl;
import repository.contract.ContractListImpl;
import repository.customer.CustomerListImpl;
import repository.pay.PayListImpl;

public class ReportAccidentService extends UnicastRemoteObject implements ReportAccidentServiceIF {
	private final ContractListImpl contractList;
	private final CustomerListImpl customerList;
	private final PayListImpl payList;
	private final AccidentListImpl accidentList;
    public ReportAccidentService(ContractListImpl contractList,CustomerListImpl customerList,PayListImpl payList,AccidentListImpl accidentList) throws RemoteException {
    	this.contractList = contractList;
    	this.customerList = customerList;
    	this.payList = payList;
    	this.accidentList = accidentList;
    }
	public ArrayList<Contract> getContractByCustomerId(int customerId) throws RemoteException,EmptyListException {
		if(this.contractList.findByCustomerId(customerId)==null)
			new EmptyListException("가입한 계약이 존재하지 않습니다.");
		return this.contractList.findByCustomerId(customerId);
	}

	@Override
	public boolean reportAccident(Accident accident) throws RemoteException {
		this.accidentList.add(accident);
		return true;
	}
	
}
