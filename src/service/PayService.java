package service;

import dao.PayDao;
import domain.Contract;
import domain.Pay;
import enumeration.contract.PaymentCycle;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PayService extends UnicastRemoteObject implements PayServiceIF{
    private final PayDao payDao;
    private ContractService contractService;
    public PayService(PayDao payDao) throws RemoteException {
        this.payDao = payDao;
    }
    @Override
    public void setContractService(ContractService contractService) throws RemoteException {
        this.contractService = contractService;
    }
    @Override
    public int pay(Contract contract, Pay pay) throws RemoteException{

        int cycle= PaymentCycle.getCycle(contract.getPayCycle());
        Timestamp deadline= contract.getPaymentDeadline();
        LocalDateTime newDeadline = deadline.toLocalDateTime();
        newDeadline = newDeadline.plus(cycle, ChronoUnit.MONTHS);
        boolean isSuccess = contractService.setPaymentDeadline(contract.getId(), Timestamp.valueOf(newDeadline));
        if(!isSuccess) return 0;

        return this.payDao.add(pay);
    }
}
