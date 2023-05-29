package ui;

import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomerUi {
	
	private final String customerId;
	private final ServiceContainer serviceContainer;
	private final BufferedReader userInput;
	
	public CustomerUi(String customerId, ServiceContainer serviceContainer, BufferedReader userInput) {
		this.customerId = customerId;
		this.serviceContainer = serviceContainer;
		this.userInput = userInput;
	}

	public void printMenu() throws IOException {
//		while(true) {
//			System.out.println("******************** 상품관리자 메인 메뉴 *********************");
//			System.out.println("1. 인가하기");
//			System.out.println("2. 상품관리하기");
//			System.out.println("0. 로그아웃");
//			System.out.println("x. 종료");
//			switch(userInput.readLine().trim()) {
//				case "1" : printApplyInsurance();  break;
//				case "2" : printManageMenu(); break;
//				case "0" : return;
//				case "x" : System.exit(0);
//				default : System.err.println("잘못된 입력입니다.");
//			}
//		}
	}
}
