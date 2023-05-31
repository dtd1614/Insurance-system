package service;

import domain.Employee;
import exception.DataDuplicationException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EmployeeServiceIF  extends Remote {
    boolean registerEmployee(Employee employee) throws RemoteException, DataDuplicationException;

    Employee loginEmployee(String id, String password) throws RemoteException, NoDataException;
}
