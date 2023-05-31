package service;

import dao.PayDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PayService extends UnicastRemoteObject implements PayServiceIF{
    private final PayDao payDao;
    protected PayService(PayDao payDao) throws RemoteException {
        this.payDao = payDao;
    }
//    @Override
//    public String PayByVirtualAccount(int contractId) throws RemoteException {
//        Random random = new Random();
//        String accountNumber = "";
//        int randomNumber;
//        for(int i=0;i<10;i++) {
//            randomNumber=random.nextInt();
//            accountNumber=accountNumber+Integer.toString(randomNumber);
//        }
//        Pay pay = new Pay(contractId, PaymentMethod.Account, Integer.parseInt(accountNumber), -1, PayStatus.NonPayment);
//        this.payDAO.add(pay);
//        return accountNumber;
//    }
//    @Override
//    public boolean PayByCreditcard(int contractId,String creditCarditNumber, String expirationDate, String cvc) throws RemoteException, NoDataException {
//        Pay pay = new Pay(contractId, PaymentMethod.Card, -1,Integer.parseInt(creditCarditNumber), PayStatus.NonPayment);
//        this.payDAO.add(pay);
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
//        Pay pay=this.payDAO.findById(payId);
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
