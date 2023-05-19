package repository.contract;

import domain.Contract;

import java.util.ArrayList;

public interface ContractList {
    public int add(Contract contract);

    public boolean delete();

    public ArrayList<Contract> retrieve();

    public boolean update();
}
