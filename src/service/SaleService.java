package service;

import domain.Sale;
import repository.SaleList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SaleService extends UnicastRemoteObject implements SaleServiceIF {
    private final SaleList saleList;
    public SaleService(SaleList saleList) throws RemoteException {
        this.saleList = saleList;
    }
//    @Override
    public boolean Propose(String saleEmployeeId, String customerId, int insuranceId, String message) throws RemoteException {
        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        if(saleList.add(sale) == 0){
            return false;
        }else{
            return true;
        }
    }
}
