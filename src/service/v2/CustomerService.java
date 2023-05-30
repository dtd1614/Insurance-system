package service.v2;

import repository.customer.CustomerListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerService extends UnicastRemoteObject implements CustomerServiceIF{
    private final CustomerListImpl customerList;
    public CustomerService(CustomerListImpl customerList) throws RemoteException {
        this.customerList = customerList;
    }
}
