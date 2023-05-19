import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import service.ServiceContainer;
import ui.LoginUi;
public class Main {
	public static void main(String[] args) {
		try {
			LoginUi loginUI = new LoginUi(new ServiceContainer(), new BufferedReader(new InputStreamReader(System.in)));
			loginUI.printMenu();
			//git test
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
