package service;

import domain.Info.CustomerInfo;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerInfoServiceIF extends Remote {
    CustomerInfo getInfo(int infoId) throws RemoteException, NoDataException;

    int makeInfo(CustomerInfo customerInfo) throws RemoteException;
}
