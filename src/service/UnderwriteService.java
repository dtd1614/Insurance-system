package service;

import domain.Contract;
import domain.Info.Info;
import domain.customer.Customer;
import enumeration.contract.ContractStatus;
import exception.EmptyListException;
import exception.NoDataException;
import repository.contract.ContractListImpl;
import repository.customer.CustomerListImpl;
import repository.info.InfoListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UnderwriteService extends UnicastRemoteObject implements UnderwriteServiceIF {
    private final ContractListImpl contractList;
    private final CustomerListImpl customerList;
    private final InfoListImpl infoList;
    public UnderwriteService(ContractListImpl contractList, CustomerListImpl customerList, InfoListImpl infoList) throws RemoteException {
        this.contractList = contractList;
        this.customerList = customerList;
        this.infoList = infoList;
    }

    @Override
    public ArrayList<Contract> getApplyContractList() throws RemoteException, EmptyListException {
        ArrayList<Contract> contractList = this.contractList.findByStatus(ContractStatus.Apply);
        if(contractList.isEmpty()) throw new EmptyListException("신청목록이 없습니다.");
        return contractList;
    }

    @Override
    public Customer getCustomer(String customerId) throws RemoteException, NoDataException {
        Customer customer = customerList.findById(customerId);
        if(customer==null) throw new NoDataException("존재하지 않는 고객입니다.");
        return customer;
    }

    @Override
    public Info getInfo(int infoId) throws RemoteException, NoDataException {
        Info info = infoList.findById(infoId);
        if(info==null) throw new NoDataException("존재하지 않는 고객입니다.");
        return info;
    }

    @Override
    public boolean Underwrite(int contractId) throws RemoteException {
        return contractList.update(contractId, ContractStatus.Underwrite);
    }
}
