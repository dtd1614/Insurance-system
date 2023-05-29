package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import domain.Accident;
import domain.Contract;
import enumeration.contract.ContractStatus;
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
		ArrayList<Contract> contractList = this.contractList.findByCustomerId(customerId);
		for(Contract contract:contractList) {
			if(!contract.getStatus().equals(ContractStatus.Conclude)) {
				contractList.remove(contract);
			}
		}
		if(contractList.size()==0)
			new EmptyListException("가입한 계약이 존재하지 않습니다.");
		return contractList;
	}

	@Override
	public boolean reportAccident(Accident accident) throws RemoteException {
		this.accidentList.add(accident);
		return true;
	}
	
}
