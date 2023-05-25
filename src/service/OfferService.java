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
    public boolean Propose(String saleEmployeeId, String customerId, int insuranceId, String message) throws IOException {

        customerId = Show_Customer_Information();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Customer customer = customerList.findById(customerId);
        System.out.print("고객 상세 정보");
        System.out.print(customer.getPhoneNumber() + " "); System.out.print(customer.getEmail() + " ");  System.out.print(customer.isHasHome() + " "); System.out.print(customer.isHasWorkplace() + " ");

        if (customer.isHasHome() == true) {//주택보험 보여주기
        }
        if (customer.isHasWorkplace() == true) {//사업장 보험 보여주기
        }
        if(customer.isHasWorkplace() == false && customer.isHasHome() == true){
            System.out.println("고객의 조건에 맞는 보험이 존재하지 않습니다.");
            Show_Customer_Information();
        }
        System.out.println("사원 번호를 입력하세요");
        saleEmployeeId = reader.readLine();

        System.out.println("고객에게 추천할 보험 ID를 선택하세요");
        insuranceId = Integer.parseInt(reader.readLine());

        System.out.println("제목을 입력하세요");
        String name = reader.readLine();

        System.out.println("고객에게 전할 메세지를 입력하세요");
        message = reader.readLine();

        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        saleList.add(sale);

        return true;
    }

    @Override
    public String Show_Customer_Information() {
        ArrayList<Customer> customerList = new ArrayList<>();
        String selectedCustomerId = "";
        int i = 1;
        for (Customer element: customerList) {
            System.out.print(i + " " + ":" + " ");
            System.out.print(element.getId() + " ");  System.out.print(element.getName() + " ");i++;
        }
        System.out.print("고객을 선택하세요 고객ID : ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            selectedCustomerId = reader.readLine();
            if(this.customerList.findById(selectedCustomerId)==null){
                System.out.print("잘못된 선택입니다.");
                Show_Customer_Information();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedCustomerId;
    }

}
