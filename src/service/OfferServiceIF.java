package service;

import domain.customer.Customer;
import exception.NoDataException;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OfferServiceIF extends Remote {

    boolean Propose(String saleEmployeeId, String customerId, int insuranceId, String message) throws IOException;
//    String Show_Customer_Information();
    Customer getCustomer(String selectedCustomerId) throws RemoteException, NoDataException;
}
