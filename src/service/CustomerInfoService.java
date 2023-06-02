package service;

import domain.customerInfo.CustomerInfo;
import domain.customerInfo.HomeCustomerInfo;
import exception.NoDataException;
import dao.CustomerInfoDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerInfoService extends UnicastRemoteObject implements CustomerInfoServiceIF {
    private final CustomerInfoDao customerInfoDao;
    private CustomerService customerService;
    public CustomerInfoService(CustomerInfoDao customerInfoDao) throws RemoteException {
        this.customerInfoDao = customerInfoDao;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Override
    public CustomerInfo getInfo(int infoId) throws RemoteException, NoDataException {
        CustomerInfo customerInfo = customerInfoDao.findById(infoId);
        if(customerInfo ==null) throw new NoDataException("! 존재하지 않는 정보입니다.");
        return customerInfo;
    }
    @Override
    public int makeInfo(CustomerInfo customerInfo) throws RemoteException {
        boolean isSuccess = false;
        if(customerInfo instanceof HomeCustomerInfo) isSuccess = customerService.setHasHome(customerInfo.getCustomerId(), true);
        else isSuccess = customerService.setHasWorkplace(customerInfo.getCustomerId(), true);
        if(!isSuccess) return 0;
        return customerInfoDao.add(customerInfo);
    }
}
