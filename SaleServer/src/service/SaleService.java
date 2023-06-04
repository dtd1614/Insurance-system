package service;

import dao.SaleDao;
import domain.Sale;
import exception.EmptyListException;
import exception.TimeDelayException;

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
    public ArrayList<Sale> getSaleList(String customerId) throws RemoteException, EmptyListException, TimeDelayException {
        long beforeTime = System.currentTimeMillis();

        ArrayList<Sale> saleList = new ArrayList<>();
        for(Sale sale : this.saleDao.retrieve()){
            if(sale.getCustomerId().equals(customerId)) saleList.add(sale);
        }
        if(saleList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");

//        try {Thread.sleep(7000);}
//        catch (InterruptedException e) {throw new RuntimeException(e);}
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) throw new TimeDelayException("! 시간지연으로 목록을 불러오지 못했습니다. 다시 시도해주세요.");

        return saleList;
    }
}