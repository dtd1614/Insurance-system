package repository;

import java.util.ArrayList;

import domain.Employee;

public class EmployeeList extends DBConnector{

	private final ArrayList<Employee> employeeList  = new ArrayList<>();
	public boolean add(Employee employee){
		return employeeList.add(employee);
	}
	public ArrayList<Employee> retrieve(){
		return employeeList;
	}
	public Employee findById(String id) {
		for(Employee employee : employeeList) {
			if(employee.getId().equals(id)) return employee;
		}
		return null;
	}
}