//package ui;
//
//import domain.Insurance;
//import enumeration.insurance.InsuranceType;
//import enumeration.policy.PolicyType;
//import service.ServiceContainer;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class CompensateManagerUi {
//    private final String employeeId;
//    private final ServiceContainer serviceContainer;
//    private final BufferedReader userInput;
//
//    public CompensateManagerUi(String employeeId, ServiceContainer serviceContainer, BufferedReader userInput) {
//        this.employeeId = employeeId;
//        this.serviceContainer = serviceContainer;
//        this.userInput = userInput;
//    }
//
//    public void printMenu() throws IOException {
//        while(true) {
//            System.out.println("******************** 보험관리자 메인 메뉴 *********************");
//            System.out.println("1. 보상지침을 수립하다");
//            System.out.println("2. 보상을 결정하다");
//            System.out.println("3. 사고를 접수하다");
//            System.out.println("0. 로그아웃");
//            System.out.println("x. 종료");
//            switch(userInput.readLine().trim()) {
//                case "1" : printInsuranceTypeMenu(); break;
//                case "2" : printPayCompensation();; break;
//                case "3" : printReportAccident(); break;
//                case "0" : return;
//                case "x" : System.exit(0);
//                default : System.err.println("잘못된 입력입니다.");
//            }
//        }
//    }
//
//    private void printInsuranceTypeMenu() throws IOException { // 보험 유형 목록 가져오기
//        while(true) {
//            ArrayList<Policy> insuranceTypeList = serviceContainer.getMakePolicyService().getInsuranceList(InsuranceType.WorkplaceFire);
//            if(insuranceTypeList.isEmpty()) {System.err.println("보상 지침을 수립할 보험이 없습니다."); return;}
//            System.out.println("******************** 보험 유형 목록 *********************");
//            System.out.println("보험유형의 아이디를 입력하세요. 뒤로가려면 0을 입력하세요.");
//            System.out.println("보험유형");
//            for (Insurance insurance : insuranceTypeList) {
//                System.out.println(insurance.getType().getName());
////                        insurance.getId()
////                        + "\t" + insurance.getName()
////                        + "\t" + insurance.getType().getName());
//            }
//            System.out.print("보험유형 아이디 : ");
//            int id;
//            try {
//                id = Integer.parseInt(userInput.readLine().trim());
//            }catch (NumberFormatException e) {
//                System.err.println("잘못된 입력입니다."); continue;
//            }
//            if(id==0) return;
//            Insurance selectedInsurance = null;
//            for(Insurance insurance:insuranceTypeList) {
//                if(insurance.getId()==id)selectedInsurance = insurance;
//            }
//            if(selectedInsurance==null){System.err.println("잘못된 입력입니다."); continue;}
//            printInsuranceTypeDetail(selectedInsurance);
//        }
//    }
//
//    private void printPayCompensation() {
//    }
//
//    private void printReportAccident() {
//    }
//
//    private void printInsuranceTypeDetail(Insurance selectedInsurance) throws IOException{ // 보험유형 목록 보여주기
//        while(true) {
//            System.out.println("******************** 보상 지침 목록 *********************");
//            System.out.println("보험유형 상세내역을 확인하고 버튼을 선택하세요.");
//            System.out.println("[보상지침목록]");
//            System.out.println("유형 : " + selectedInsurance.getType());
//            System.out.println("\n[버튼]");
//            System.out.println("1. 수립하기");
//            System.out.println("0. 뒤로가기");
//            String choice = userInput.readLine().trim();
//            if (choice.equals("0")) {
//                return;
//            } else if(choice.equals("1")){
//                boolean isSuccess = serviceContainer.getMakePolicyService().makeCompensatePolicy(selectedInsurance.getId(), PolicyType.Compensation);
//                if(isSuccess==true) System.out.println("보상지침이 수립되었습니다.");
//                else System.out.println("수립할 보상지침이 없습니다.");
//                return;
//            } else {
//                System.err.println("잘못된 입력입니다.");
//            }
//        }
//    }
//}