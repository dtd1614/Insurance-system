package service;

import domain.Contract;
import domain.Insurance;
import domain.customer.Customer;
import enumeration.contract.ContractStatus;
import exception.NoDataException;
import repository.contract.ContractListImpl;
import repository.customer.CustomerListImpl;
import repository.insurance.InsuranceListImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ConcludeService extends UnicastRemoteObject implements ConcludeServiceIF {
    private final CustomerListImpl customerList;
    private final InsuranceListImpl insuranceList;
    private final ContractListImpl contractList;

    public ConcludeService(CustomerListImpl customerList, InsuranceListImpl insuranceList, ContractListImpl contractList) throws RemoteException {
        this.customerList = customerList;
        this.insuranceList = insuranceList;
        this.contractList = contractList;
    }

    @Override
    public boolean conclude(int selectedId) {
        Contract contract = this.contractList.findById(selectedId);
        contract.setStatus(ContractStatus.valueOf("Conclude"));
        return true;
    }

    @Override
    public Contract getContract(int selectedContractId) throws RemoteException, NoDataException {
        Contract contract = this.contractList.findById(selectedContractId);
        if(contract == null){ throw new NoDataException("존재하지 않는 계약 번호 입니다.");
        } else {
            return contract;
        }
    }

    @Override
    public Insurance getInsurance(int selectedInsuranceId) throws RemoteException, NoDataException {
        Insurance insurance = this.insuranceList.findById(selectedInsuranceId);
        if(insurance == null){ throw new NoDataException("존재 하지 않는 보험 번호 입니다.");
        } else {
            return insurance;
        } //getInsurance로 함수 고치기
    }

}
