package service;

import domain.Contract;
import domain.Insurance;
import enumeration.contract.ContractStatus;
import exception.EmptyListException;
import exception.NoDataException;
import repository.ContractList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ContractService extends UnicastRemoteObject implements ContractServiceIF{
    private final ContractList contractList;
    public ContractService(ContractList contractList) throws RemoteException {
        this.contractList = contractList;
    }
    public ArrayList<Contract> getContractList(ContractStatus status) throws EmptyListException {
        ArrayList<Contract> contractList = this.contractList.findByStatus(status);
        if(contractList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return contractList;
    }
    //    @Override
    public ArrayList<Contract> getContractList(String customerId) throws RemoteException, EmptyListException {
        ArrayList<Contract> contractList = this.contractList.retrieve();
        for(Contract contract : contractList){
            if(!contract.getCustomerId().equals(customerId)) contractList.remove(contract);
        }
        if(contractList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return contractList;
    }
    //    @Override
    public Contract getContract(int contractId) throws RemoteException, NoDataException {
        Contract contract = this.contractList.findById(contractId);
        if(contract == null){ throw new NoDataException("존재하지 않는 계약입니다.");
        } else {
            return contract;
        }
    }
//    @Override
    public int applyInsurance(Contract contract) throws RemoteException {
        return contractList.add(contract);
    }
//    @Override
    public boolean conclude(int id) {
        Contract contract = this.contractList.findById(id);
        contract.setStatus(ContractStatus.Conclude);
        return true;
    }

    //    @Override
    public boolean examineUnderwrite(int contractId, ContractStatus status) throws RemoteException {
        return contractList.update(contractId, status);
    }

//    @Override
    public ArrayList<Contract> getUnpaidContractList(String customerId) throws RemoteException, EmptyListException {
        ArrayList<Contract> unpaidContractList=this.contractList.findByCustomerId(customerId);
        if(unpaidContractList.size()==0)
            throw new EmptyListException("가입한 계약이 존재하지 않습니다.");
        else {
            Timestamp now=new Timestamp(System.currentTimeMillis());
            for(Contract contract:unpaidContractList) {
                Timestamp deadlineStamp=contract.getPaymentDeadline();
                LocalDateTime deadline = deadlineStamp.toLocalDateTime();
                LocalDateTime nowTime = now.toLocalDateTime();
                long daysDifference = ChronoUnit.DAYS.between(nowTime,deadline);
                if(!contract.getStatus().equals(ContractStatus.Conclude)||daysDifference>=7) {
                    unpaidContractList.remove(contract);
                }
            }
            if(unpaidContractList.size()==0)
                throw new EmptyListException("돈을 지불해야 할 계약이 존재하지 않습니다.");
            return unpaidContractList;
        }
    }

//    @Override
    public ArrayList<Contract> getContractByCustomerId(String customerId) throws RemoteException,EmptyListException {
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


}
