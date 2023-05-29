package service.v2;

import repository.employee.EmployeeListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EmployeeService extends UnicastRemoteObject implements EmployeeServiceIF{
    private final EmployeeListImpl employeeList;
    public EmployeeService(EmployeeListImpl employeeList) throws RemoteException {

        this.employeeList = employeeList;
    }
}
