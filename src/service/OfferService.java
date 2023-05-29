package service;

import domain.Employee;
import domain.Sale;
import domain.customer.Customer;
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
    public Customer findByCustomerId(String selectedCustomerId) {
        Customer customer = customerList.findById(selectedCustomerId);
        if(customer == null){
            return null;
        }else {
            return customer;
        }
    }

    @Override
    public boolean Propose(String saleEmployeeId, String customerId, int insuranceId, String message) throws IOException {
        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        if(saleList.add(sale) == 0){
            return false;
        }else{
            return true;
        }
    }

//    @Override
//    public String Show_Customer_Information() {
//        ArrayList<Customer> customerList = new ArrayList<>();
//        String selectedCustomerId = "";
//        int i = 1;
//        for (Customer element: customerList) {
//            System.out.print(i + " " + ":" + " ");
//            System.out.print(element.getId() + " ");  System.out.print(element.getName() + " ");i++;
//        }
//        System.out.print("고객을 선택하세요 고객ID : ");
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            selectedCustomerId = reader.readLine();
//            if(this.customerList.findById(selectedCustomerId)==null){
//                System.out.print("잘못된 선택입니다.");
//                Show_Customer_Information();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return selectedCustomerId;
//    }

}
