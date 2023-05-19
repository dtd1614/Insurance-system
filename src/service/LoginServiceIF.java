package service;

import domain.customer.Customer;
import domain.Employee;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginServiceIF extends Remote {
    boolean registerCustomer(Customer customer) throws RemoteException;
    boolean registerEmployee(Employee employee) throws RemoteException;
    Employee loginEmployee(String id, String password) throws RemoteException;
    Customer loginCustomer(String id, String password) throws RemoteException;
}
