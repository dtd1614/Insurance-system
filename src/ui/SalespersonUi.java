package ui;

import domain.Contract;
import domain.Insurance;
import domain.Sale;
import domain.customer.Customer;
import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SalespersonUi {
    private final String employeeId;
    private final ServiceContainer serviceContainer;
    private final BufferedReader userInput;



    public SalespersonUi(String employeeId, ServiceContainer serviceContainer, BufferedReader userInput) {
        this.employeeId = employeeId;
        this.serviceContainer = serviceContainer;
        this.userInput = userInput;
    }

    public void printMenu() throws IOException {
        while(true) {
            System.out.println("******************** 영업사원 메인 메뉴 *********************");
            System.out.println("1. 보험가입 신청목록 조회");
            System.out.println("2. 제안하기");
            System.out.println("x. 종료");
            switch(userInput.readLine().trim()) {
                case "1" : Searchapplicationlist(); break;
                case "2" : ShowCustomerInformation(); break;
                case "x" : System.exit(0);
                default : System.err.println("잘못된 입력입니다.");
            }

        }
    }

    private boolean ShowCustomerInformation() throws IOException {
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
            if(serviceContainer.getofferService().findByCustomerId(selectedCustomerId)==null){
                System.out.print("잘못된 선택입니다.");
                ShowCustomerInformation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Propose(selectedCustomerId);
    }

    private boolean Propose(String selectedCustomerId) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Customer customer = serviceContainer.getofferService().findByCustomerId(selectedCustomerId);
        System.out.print("고객 상세 정보");
        System.out.print(customer.getPhoneNumber() + " "); System.out.print(customer.getEmail() + " ");  System.out.print(customer.isHasHome() + " "); System.out.print(customer.isHasWorkplace() + " ");
        if (customer.isHasHome() == true) {//주택보험 보여주기
        }
        if (customer.isHasWorkplace() == true) {//사업장 보험 보여주기
        }
        if(customer.isHasWorkplace() == false && customer.isHasHome() == true){
            System.out.println("고객의 조건에 맞는 보험이 존재하지 않습니다.");
            ShowCustomerInformation();
        }
        System.out.println("사원 번호를 입력하세요");
        String saleEmployeeId = reader.readLine();

        System.out.println("고객에게 추천할 보험 ID를 선택하세요");
        int insuranceId = Integer.parseInt(reader.readLine());

        System.out.println("제목을 입력하세요");
        String name = reader.readLine();

        System.out.println("고객에게 전할 메세지를 입력하세요");
        String message = reader.readLine();

        return serviceContainer.getofferService().Propose(saleEmployeeId, selectedCustomerId, insuranceId, message);
    }

    public boolean Searchapplicationlist() throws IOException {  //
        ArrayList<Contract> contractList = new ArrayList<>();
        int selectedContractId = Integer.parseInt(null);
        int i = 1;
        for (Contract element: contractList) {
            if(element.getStatus().getName() == "Authorize") {
                System.out.print(i + " " + ":" + " ");
                System.out.print(element.getId() + " ");
                System.out.print(element.getInsuranceId() + " ");
                i++;
            }
        }
        System.out.print("고객을 선택하세요 고객ID : ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            selectedContractId = Integer.parseInt(reader.readLine());
            if(serviceContainer.getConcludeService().findByContractId(selectedContractId)==null){
                System.out.print("잘못된 선택입니다.");
                Searchapplicationlist();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return concludeContract(selectedContractId);
    }

    public boolean concludeContract(int selectedContractId) throws IOException {
        Contract contract = serviceContainer.getConcludeService().findByContractId(selectedContractId);
        Insurance insurance = serviceContainer.getConcludeService().findByInsuranceId(contract.getInsuranceId());
        System.out.print(contract.getId() + " ");  System.out.print(contract.getInsuranceId() + " ");
        System.out.print(insurance.getName() + " ");  System.out.println(insurance.getTarget() + " ");
        System.out.println("1. 계약 체결");
        System.out.println("0. 뒤로 가기");
        switch(userInput.readLine().trim()) {
            case "1" : serviceContainer.getConcludeService().conclude(selectedContractId);
            case "0" : Searchapplicationlist();
            default: System.err.println("잘못된 입력입니다.");
        }
        return serviceContainer.getConcludeService().conclude(selectedContractId);
    }
}
