package service;

import domain.Sale;
import dao.SaleDao;
import exception.EmptyListException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SaleService extends UnicastRemoteObject implements SaleServiceIF {
    private final SaleDao saleDao;
    public SaleService(SaleDao saleDao) throws RemoteException {
        this.saleDao = saleDao;
    }
    @Override
    public int offerInsurance(String saleEmployeeId, String customerId, int insuranceId, String message) throws RemoteException {
        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        return saleDao.add(sale);
    }
    @Override
    public ArrayList<Sale> getSaleList(String customerId) throws RemoteException, EmptyListException {
        ArrayList<Sale> saleList = new ArrayList<>();
        for(Sale sale : this.saleDao.retrieve()){
            if(sale.getCustomerId().equals(customerId)) saleList.add(sale);
        }
        if(saleList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return saleList;
    }
}
