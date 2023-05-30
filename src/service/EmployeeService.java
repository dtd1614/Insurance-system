package service;

import domain.Employee;
import exception.DataDuplicationException;
import exception.EmptyListException;
import exception.NoDataException;
import repository.EmployeeList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EmployeeService extends UnicastRemoteObject implements EmployeeServiceIF{
    private final EmployeeList employeeList;
    public EmployeeService(EmployeeList employeeList) throws RemoteException {

        this.employeeList = employeeList;
    }
//    @Override
    public boolean registerEmployee(Employee employee) throws RemoteException, DataDuplicationException {
        if(employeeList.findById(employee.getId()) != null) throw new DataDuplicationException("이미 존재하는 아이디입니다.");
        return employeeList.add(employee);
    }
//    @Override
    public Employee loginEmployee(String id, String password) throws RemoteException, NoDataException {
        Employee employee = employeeList.findById(id);
        if(employee == null || !employee.getPassword().equals(password)) throw new NoDataException("존재하지 않는 계정입니다.");
        return employee;
    }
}
