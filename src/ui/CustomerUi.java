package ui;

import service.ServiceContainer;

import java.io.BufferedReader;

public class CustomerUi {
	
	private final String customerId;
	private final ServiceContainer serviceContainer;
	private final BufferedReader userInput;
	
	public CustomerUi(String customerId, ServiceContainer serviceContainer, BufferedReader userInput) {
		this.customerId = customerId;
		this.serviceContainer = serviceContainer;
		this.userInput = userInput;
	}

	public void printMenu() {
		
	}
}
