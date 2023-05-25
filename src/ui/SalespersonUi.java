package ui;

import service.OfferService;
import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

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
                case "1" : serviceContainer.getConcludeService().conclude(); break;
                case "2" : serviceContainer.getofferService().Show_Customer_Information(); break;
                case "x" : System.exit(0);
                default : System.err.println("잘못된 입력입니다.");
            }
        }
    }
}
