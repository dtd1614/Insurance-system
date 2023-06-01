package service;

import domain.Info.CustomerInfo;
import exception.NoDataException;
import dao.CustomerInfoDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerCustomerInfoService extends UnicastRemoteObject implements CustomerInfoServiceIF {
    private final CustomerInfoDao customerInfoDao;
    public CustomerCustomerInfoService(CustomerInfoDao customerInfoDao) throws RemoteException {
        this.customerInfoDao = customerInfoDao;
    }
    @Override
    public CustomerInfo getInfo(int infoId) throws RemoteException, NoDataException {
        CustomerInfo customerInfo = customerInfoDao.findById(infoId);
        if(customerInfo ==null) throw new NoDataException("! 존재하지 않는 정보입니다.");
        return customerInfo;
    }
    @Override
    public int makeInfo(CustomerInfo customerInfo) throws RemoteException {
        return customerInfoDao.add(customerInfo);
    }
}
