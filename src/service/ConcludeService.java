package service;

import domain.Contract;
import domain.Insurance;
import domain.customer.Customer;
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
    public int Searchapplicationlist() {  //
        ArrayList<Contract> contractList = new ArrayList<>();
        int selectedContractId = Integer.parseInt(null);
        int i = 1;
        for (Contract element: contractList) {
            System.out.print(i + " " + ":" + " "); //인수심사 결과
            System.out.print(element.getId() + " ");  System.out.print(element.getInsuranceId() + " ");i++;
        }
        System.out.print("고객을 선택하세요 고객ID : ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            selectedContractId = Integer.parseInt(reader.readLine());
            if(this.contractList.findById(selectedContractId)==null){
                System.out.print("잘못된 선택입니다.");
                Searchapplicationlist();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedContractId;
    }

    @Override
    public boolean conclude() {
        int selectedContractId;
        selectedContractId = Searchapplicationlist();
        Contract contract = this.contractList.findById(selectedContractId);
        Insurance insurance = this.insuranceList.findById(contract.getInsuranceId());
       // System.out.print(i + " " + ":" + " "); //인수심사 결과
        System.out.print(contract.getId() + " ");  System.out.print(contract.getInsuranceId() + " ");
        System.out.print(insurance.getName() + " ");  System.out.println(insurance.getTarget() + " ");
        System.out.print("1. 계약 체결");
        
        return false;
    }
}
