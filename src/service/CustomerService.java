package service;

import domain.customer.Customer;
import exception.DataDuplicationException;
import exception.NoDataException;
import repository.CustomerList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerService extends UnicastRemoteObject implements CustomerServiceIF{
    private final CustomerList customerList;
    public CustomerService(CustomerList customerList) throws RemoteException {
        this.customerList = customerList;
    }
//    @Override
    public boolean registerCustomer(Customer customer) throws RemoteException, DataDuplicationException {
        if(customerList.findById(customer.getId())!= null) throw new DataDuplicationException("이미 존재하는 아이디입니다.");
        return customerList.add(customer);
    }
//    @Override
    public Customer loginCustomer(String id, String password) throws RemoteException, NoDataException {
        Customer customer = customerList.findById(id);
        if(customer == null || !customer.getPassword().equals(password)) throw new NoDataException("존재하지 않는 계정입니다.");
        return customer;
    }
//    @Override
    public Customer getCustomer(String selectedCustomerId) throws RemoteException, NoDataException {
        Customer customer = customerList.findById(selectedCustomerId);
        if(customer == null)throw new NoDataException("존재하지 않는 고객 번호 입니다.");
        return customer;
    }
}
