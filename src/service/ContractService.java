package service;

import domain.Contract;
import enumeration.contract.ContractStatus;
import enumeration.contract.PayStatus;
import exception.EmptyListException;
import exception.NoDataException;
import dao.ContractDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ContractService extends UnicastRemoteObject implements ContractServiceIF{
    private final ContractDao contractDao;
    public ContractService(ContractDao contractDao) throws RemoteException {
        this.contractDao = contractDao;
    }
    @Override
    public ArrayList<Contract> getContractList(ContractStatus status) throws RemoteException, EmptyListException {
        ArrayList<Contract> contractList = this.contractDao.findByStatus(status);
        if(contractList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return contractList;
    }
    @Override
    public ArrayList<Contract> getContractList(String customerId) throws RemoteException, EmptyListException {
        ArrayList<Contract> contractList = this.contractDao.retrieve();
        for(Contract contract : contractList){
            if(!contract.getCustomerId().equals(customerId)) contractList.remove(contract);
        }
        if(contractList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return contractList;
    }
    @Override
    public ArrayList<Contract> getContractList(String customerId, ContractStatus status) throws RemoteException, EmptyListException {
        ArrayList<Contract> contractList = this.contractDao.findByStatus(status);
        for(Contract contract : contractList){
            if(!contract.getCustomerId().equals(customerId)) contractList.remove(contract);
        }
        if(contractList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return contractList;
    }
    @Override
    public Contract getContract(int contractId) throws RemoteException, NoDataException {
        Contract contract = this.contractDao.findById(contractId);
        if(contract == null){ throw new NoDataException("존재하지 않는 계약입니다.");}
        return contract;
    }
    @Override
    public int applyInsurance(Contract contract) throws RemoteException {
        return contractDao.add(contract);
    }
    @Override
    public boolean conclude(int id) throws RemoteException, NoDataException {
        Contract contract = this.contractDao.findById(id);
        if(contract == null) throw new NoDataException("존재하지 않는 계약입니다.");

        Timestamp startDate = new Timestamp(System.currentTimeMillis());

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startDate.getTime());
        cal.add(Calendar.YEAR, contract.getTerm().getYear());
        Timestamp expirationDate = new Timestamp(cal.getTime().getTime());

        cal.setTimeInMillis(startDate.getTime());
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        Timestamp deadline = new Timestamp(cal.getTime().getTime());

        return this.contractDao.update(id,startDate,expirationDate,deadline, PayStatus.NonPayment);
    }

    @Override
    public boolean examineUnderwrite(int contractId, ContractStatus status) throws RemoteException {
        return contractDao.update(contractId, status);
    }

//    @Override
//    public ArrayList<Contract> getUnpaidContractList(String customerId) throws RemoteException, EmptyListException {
//        ArrayList<Contract> unpaidContractList=this.contractDAO.findByCustomerId(customerId);
//        if(unpaidContractList.size()==0)
//            throw new EmptyListException("가입한 계약이 존재하지 않습니다.");
//        else {
//            Timestamp now=new Timestamp(System.currentTimeMillis());
//            for(Contract contract:unpaidContractList) {
//                Timestamp deadlineStamp=contract.getPaymentDeadline();
//                LocalDateTime deadline = deadlineStamp.toLocalDateTime();
//                LocalDateTime nowTime = now.toLocalDateTime();
//                long daysDifference = ChronoUnit.DAYS.between(nowTime,deadline);
//                if(!contract.getContractStatus().equals(ContractStatus.Conclude)||daysDifference>=7) {
//                    unpaidContractList.remove(contract);
//                }
//            }
//            if(unpaidContractList.size()==0)
//                throw new EmptyListException("돈을 지불해야 할 계약이 존재하지 않습니다.");
//            return unpaidContractList;
//        }
//    }

//    @Override
//    public ArrayList<Contract> getContractByCustomerId(String customerId) throws RemoteException,EmptyListException {
//        ArrayList<Contract> contractList = this.contractDAO.findByCustomerId(customerId);
//        for(Contract contract:contractList) {
//            if(!contract.getContractStatus().equals(ContractStatus.Conclude)) {
//                contractList.remove(contract);
//            }
//        }
//        if(contractList.size()==0)
//            new EmptyListException("가입한 계약이 존재하지 않습니다.");
//        return contractList;
//    }
}
