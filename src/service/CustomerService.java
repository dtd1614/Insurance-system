package service;

import domain.Customer;
import exception.DataDuplicationException;
import exception.EmptyListException;
import exception.NoDataException;
import dao.CustomerDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CustomerService extends UnicastRemoteObject implements CustomerServiceIF{
    private final CustomerDao customerDao;
    public CustomerService(CustomerDao customerDao) throws RemoteException {
        this.customerDao = customerDao;
    }
    @Override
    public boolean registerCustomer(Customer customer) throws RemoteException, DataDuplicationException {
        if(customerDao.findById(customer.getId())!= null) throw new DataDuplicationException("! 이미 존재하는 아이디입니다.");
        return customerDao.add(customer);
    }
    @Override
    public Customer loginCustomer(String id, String password) throws RemoteException, NoDataException {
        Customer customer = customerDao.findById(id);
        if(customer == null || !customer.getPassword().equals(password)) throw new NoDataException("! 존재하지 않는 계정입니다.");
        return customer;
    }
    @Override
    public Customer getCustomer(String selectedCustomerId) throws RemoteException, NoDataException {
        Customer customer = customerDao.findById(selectedCustomerId);
        if(customer == null)throw new NoDataException("! 존재하지 않는 고객 번호 입니다.");
        return customer;
    }
    @Override
    public ArrayList<Customer> getCustomerList() throws RemoteException, EmptyListException {
        ArrayList<Customer> customerList = customerDao.retrieve();
        if (customerList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return customerList;
    }
}
