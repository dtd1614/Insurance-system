package dao;

import java.util.ArrayList;

import domain.Contract;
import domain.Employee;

public class EmployeeDao extends Dao {

	private final ArrayList<Employee> employeeList  = new ArrayList<>();
	public boolean add(Employee employee){
		return employeeList.add(employee);
	}
	public ArrayList<Employee> retrieve(){
		ArrayList<Employee> employeeList = new ArrayList<>();
		for(Employee employee : this.employeeList){
			employeeList.add(employee);
		}
		return employeeList;
	}
	public Employee findById(String id) {
		for(Employee employee : employeeList) {
			if(employee.getId().equals(id)) return employee;
		}
		return null;
	}
}