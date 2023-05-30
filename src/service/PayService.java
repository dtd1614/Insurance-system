package service;

import domain.Contract;
import domain.Pay;
import enumeration.pay.PayStatus;
import enumeration.pay.PaymentMethod;
import exception.NoDataException;
import repository.PayList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class PayService extends UnicastRemoteObject implements PayServiceIF{
    private final PayList payList;
    protected PayService(PayList payList) throws RemoteException {
        this.payList = payList;
    }
//    @Override
    public String PayByVirtualAccount(int contractId) throws RemoteException {
        Random random = new Random();
        String accountNumber = "";
        int randomNumber;
        for(int i=0;i<10;i++) {
            randomNumber=random.nextInt();
            accountNumber=accountNumber+Integer.toString(randomNumber);
        }
        Pay pay = new Pay(contractId, PaymentMethod.Account, Integer.parseInt(accountNumber), -1, PayStatus.NonPayment);
        this.payList.add(pay);
        return accountNumber;
    }
//    @Override
//    public boolean PayByCreditcard(int contractId,String creditCarditNumber, String expirationDate, String cvc) throws RemoteException, NoDataException {
//        Pay pay = new Pay(contractId, PaymentMethod.Card, -1,Integer.parseInt(creditCarditNumber), PayStatus.NonPayment);
//        this.payList.add(pay);
//
//        Contract paiedContract= getContract(pay.getContractId());
//        if(paiedContract==null) {
//            new NoDataException("해당 계약이 없습니다.");
//        }
//        if(paiedContract.paied())
//            return pay.paied();
//        else
//            return false;
//    }
//    @Override
//    public boolean checkPayed(int payId) throws RemoteException, NoDataException {
//        Pay pay=this.payList.findById(payId);
//        Contract paiedContract= getContract(pay.getContractId());
//        if(paiedContract==null) {
//            new NoDataException("해당 계약이 없습니다.");
//        }
//        if(paiedContract.paied())
//            return pay.paied();
//        else
//            return false;
//    }
}
