package repository;

import domain.Contract;
import domain.customer.Customer;
import enumeration.contract.ContractStatus;


import java.util.ArrayList;

public class ContractList extends DBConnector{
    private final ArrayList<Contract> contractList = new ArrayList<>();
    public int add(Contract contract) {
        if(contractList.size()==0)contract.setId(1);
        else {contract.setId(contractList.get(contractList.size()-1).getId()+1);}
        if(contractList.add(contract)) return contract.getId();
        else {return 0;}
    }
    public ArrayList<Contract> retrieve(){
        return contractList;
    }

    public Contract findById(int id) {
        for (Contract contract : contractList) {
            if (contract.getId() == id) return contract;
        }
        return null;
    }
	public ArrayList<Contract> findByCustomerId(String customerId) {
		ArrayList<Contract> contractList = new ArrayList<Contract>();
		for(Contract contract:this.contractList) {
			if(contract.getCustomerId()==customerId) 
				contractList.add(contract);
		}
		return contractList;
	}
    public ArrayList<Contract> findByStatus(ContractStatus status) {
        ArrayList<Contract> contractListByStatus = new ArrayList<>();
        for(Contract contract : contractList) {
            if(contract.getStatus()==status) contractListByStatus.add(contract);
        }
        return contractListByStatus;
    }

    public boolean update(int id, ContractStatus status) {
        for(Contract contract : contractList) {
            if(contract.getId()==id) {contract.setStatus(status); return true;}
        }
        return false;
    }
}
