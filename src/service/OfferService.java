package service;

import domain.Employee;
import domain.Sale;
import domain.customer.Customer;
import exception.NoDataException;
import repository.customer.CustomerListImpl;
import repository.employee.EmployeeListImpl;
import repository.insurance.InsuranceListImpl;
import repository.sale.SaleListImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class OfferService extends UnicastRemoteObject implements OfferServiceIF {

    private final CustomerListImpl customerList;
    private final InsuranceListImpl insuranceList;
    private final EmployeeListImpl employeeList;
    private final SaleListImpl saleList;


    public OfferService(CustomerListImpl customerList, InsuranceListImpl insuranceList, SaleListImpl saleList, EmployeeListImpl employeeList) throws RemoteException {
        this.customerList = customerList;
        this.insuranceList = insuranceList;
        this.saleList = saleList;
        this.employeeList = employeeList;
    }

    @Override
    public Customer getCustomer(String selectedCustomerId) throws RemoteException, NoDataException {
        Customer customer = customerList.findById(selectedCustomerId);
        if(customer == null){ throw new NoDataException("존재하지 않는 고객 번호 입니다.");
        }else {
            return customer;
        }
    }

    @Override
    public boolean Propose(String saleEmployeeId, String customerId, int insuranceId, String message) throws RemoteException {
        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        if(saleList.add(sale) == 0){
            return false;
        }else{
            return true;
        }
    }


}
