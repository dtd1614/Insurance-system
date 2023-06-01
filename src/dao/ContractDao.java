package dao;

import domain.Contract;
import enumeration.contract.ContractStatus;


import java.sql.Timestamp;
import java.util.ArrayList;

public class ContractDao extends Dao {
    private final ArrayList<Contract> contractList = new ArrayList<>();
    public int add(Contract contract) {
        if(contractList.size()==0)contract.setId(1);
        else {contract.setId(contractList.get(contractList.size()-1).getId()+1);}
        if(contractList.add(contract)) return contract.getId();
        else {return 0;}
    }
    public ArrayList<Contract> retrieve(){
        ArrayList<Contract> contractList = new ArrayList<>();
        for(Contract contract : this.contractList){
            contractList.add(contract);
        }
        return contractList;
    }

    public Contract findById(int id) {
        for (Contract contract : contractList) {
            if (contract.getId() == id) return contract;
        }
        return null;
    }
	public ArrayList<Contract> findByCustomerId(String customerId) {
		ArrayList<Contract> contractList = new ArrayList<>();
		for(Contract contract:this.contractList) {
			if(contract.getCustomerId().equals(customerId))
				contractList.add(contract);
		}
		return contractList;
	}
    public ArrayList<Contract> findByStatus(ContractStatus status) {
        ArrayList<Contract> contractListByStatus = new ArrayList<>();
        for(Contract contract : contractList) {
            if(contract.getContractStatus()==status) contractListByStatus.add(contract);
        }
        return contractListByStatus;
    }

    public boolean update(int id, ContractStatus status) {
        for(Contract contract : contractList) {
            if(contract.getId()==id) {contract.setContractStatus(status); return true;}
        }
        return false;
    }

    public boolean update(int id, Timestamp startDate, Timestamp expirationDate, Timestamp deadline, ContractStatus status) {
        for(Contract contract : this.contractList) {
            if(contract.getId()==id) {
                contract.setStartDate(startDate);
                contract.setExpirationDate(expirationDate);
                contract.setPaymentDeadline(deadline);
                contract.setContractStatus(status);
                return true;
            }
        }
        return false;
    }
}
