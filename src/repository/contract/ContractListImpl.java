package repository.contract;

import domain.Contract;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ContractListImpl implements ContractList{
    private final ArrayList<Contract> contractList = new ArrayList<>();
    @Override
    public int add(Contract contract) {
        if(contractList.size()==0)contract.setId(1);
        else {contract.setId(contractList.get(contractList.size()-1).getId()+1);}
        if(contractList.add(contract)) return contract.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<Contract> retrieve() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

	public Contract findByContractId(int contractId) {
		for(Contract contract:this.contractList) {
			if(contract.getId()==contractId)
				return contract;
		}
		return null;
	}

	public ArrayList<Contract> getUnpaidContractList(int customerId, Timestamp timestamp) {
		ArrayList<Contract> findedContractList = new ArrayList<Contract>();
		for(Contract contract:this.contractList) {
			if(contract.getCustomerId()==customerId) 
				findedContractList.add(contract);
		}
		for(Contract contract:findedContractList) {
			Timestamp a=contract.getPaymentDeadline();
			LocalDateTime deadline = a.toLocalDateTime();
	        LocalDateTime now = timestamp.toLocalDateTime();
			long daysDifference = ChronoUnit.DAYS.between(now,deadline);
			if(daysDifference>=7) {
				findedContractList.remove(contract);
			}
		}
		return findedContractList;
	}
}
