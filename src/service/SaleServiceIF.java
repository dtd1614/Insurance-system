package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SaleServiceIF  extends Remote {
    int offerInsurance(String saleEmployeeId, String customerId, int insuranceId, String message) throws RemoteException;
}
