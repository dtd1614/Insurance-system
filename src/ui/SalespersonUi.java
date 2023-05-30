package ui;

import domain.Contract;
import domain.Info.HomeInfo;
import domain.Info.Info;
import domain.Info.WorkplaceInfo;
import domain.Insurance;
import domain.customer.Customer;
import enumeration.contract.ContractStatus;
import exception.EmptyListException;
import exception.NoDataException;
import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
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

    public void printMenu() throws IOException{
        while(true) {
            System.out.println("******************** 영업사원 메인 메뉴 *********************");
            System.out.println("1. 계약체결");
            System.out.println("2. 제안");
            System.out.println("3. 로그아웃");
            System.out.println("x. 종료");
            switch(userInput.readLine().trim()) {
                case "1" : printConcludeMenu();break;
                case "2" : printOfferMenu(); break;
                case "0" : return;
                case "x" : System.exit(0);
                default : System.err.println("잘못된 입력입니다.");
            }
        }
    }
    public void printConcludeMenu() throws IOException{
        while (true) {
            ArrayList<Contract> contractList = null;
            try {contractList = serviceContainer.getContractService().getContractList(ContractStatus.Underwrite);}
            catch (EmptyListException e) {System.err.println(e.getMessage()); return;}
            System.out.println("******************** 계약체결 메뉴 *********************");
            System.out.println("체결할 계약의 아이디를 입력하세요. 뒤로가려면 0을 입력하세요.");
            System.out.println("아이디\t고객아이디\t보험아이디");
            for(Contract contract : contractList) {
                System.out.println(contract.getId()
                        + "\t" + contract.getCustomerId()
                        + "\t" + contract.getInsuranceId());
            }
            System.out.print("계약 아이디 : ");
            int id;
            try {id = Integer.parseInt(userInput.readLine().trim());}
            catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
            if(id==0) return;
            Contract selectedContract = null;
            for(Contract contract : contractList) {if(contract.getId()==id) selectedContract = contract;}
            if(selectedContract==null){System.err.println("잘못된 입력입니다."); continue;}
            printContractDetail(selectedContract);
        }
    }
    private void printContractDetail(Contract contract) throws IOException {
        while (true) {
            System.out.println("******************** 계약 상세내용 *********************");
            System.out.println("계약 상세내역을 확인하고 버튼을 선택하세요.");
            Customer customer = null;
            Insurance insurance = null;
            try {
                customer = serviceContainer.getCustomerService().getCustomer(contract.getCustomerId());
                insurance = serviceContainer.getInsuranceService().getInsurance(contract.getInsuranceId());
            } catch (NoDataException e) {
                System.err.println(e.getMessage()); return;
            }
            System.out.println("고객 아이디 : " + customer.getId()
                    + "\n고객 이름 : " + customer.getName()
                    + "\n보험 아이디 : " + insurance.getId()
                    + "\n보험 이름 : " + insurance.getName()
                    + "\n보험 유형 : " + insurance.getType().getName());

            System.out.println("\n[버튼]");
            System.out.println("1. 계약체결");
            System.out.println("0. 뒤로가기");
            String choice = userInput.readLine().trim();
            if (choice.equals("0")) {
                return;
            } else if(choice.equals("1")){
                boolean isSuccess = serviceContainer.getContractService().conclude(contract.getId());
                if(isSuccess) {System.out.println("체결되었습니다."); return;}
                else {System.out.println("체결이 실패되었습니다."); return;}
            } else {
                System.err.println("잘못된 입력입니다.");
            }
        }
    }
    private void printOfferMenu() {
    }
}
