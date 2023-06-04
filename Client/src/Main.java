import service.ServiceContainer;
import ui.LoginUi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class Main {
	public static void main(String[] args) throws IOException {
		LoginUi loginUI = null;
		loginUI = new LoginUi(new ServiceContainer(), new BufferedReader(new InputStreamReader(System.in)));
		loginUI.printMenu();
	}
}
