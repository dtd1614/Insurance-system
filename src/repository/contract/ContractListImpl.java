package repository.contract;

import domain.Contract;

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
}
