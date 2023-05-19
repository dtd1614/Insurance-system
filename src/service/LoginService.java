package service;


import domain.customer.Customer;
import domain.Employee;
import repository.customer.CustomerListImpl;
import repository.employee.EmployeeListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginService extends UnicastRemoteObject implements LoginServiceIF {
    private final CustomerListImpl customerList;
    private final EmployeeListImpl employeeList;
    public LoginService(CustomerListImpl customerList, EmployeeListImpl employeeList) throws RemoteException {
        this.customerList = customerList;
        this.employeeList = employeeList;
    }

    @Override
    public boolean registerCustomer(Customer customer) throws RemoteException {
        if(customerList.findById(customer.getId())!= null) return false;
        if(employeeList.findById(customer.getId())!=null) return false;
        return customerList.add(customer);
    }
    @Override
    public boolean registerEmployee(Employee employee) throws RemoteException {
        if(employeeList.findById(employee.getId())!= null) return false;
        if(customerList.findById(employee.getId())!= null) return false;
        return employeeList.add(employee);
    }
    @Override
    public Employee loginEmployee(String id, String password) throws RemoteException {
        Employee employee = employeeList.findById(id);
        if(employee == null || !employee.getPassword().equals(password)) return null;
        return employee;
    }
    @Override
    public Customer loginCustomer(String id, String password) throws RemoteException {
        Customer customer = customerList.findById(id);
        if(customer == null || !customer.getPassword().equals(password)) return null;
        return customer;
    }
}
