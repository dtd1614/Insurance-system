package repository.employee;

import java.util.ArrayList;

import domain.Employee;

public class EmployeeListImpl implements EmployeeList {

	private final ArrayList<Employee> employeeList  = new ArrayList<>();

	public boolean add(Employee employee){
		return employeeList.add(employee);
	}

	public boolean delete(){
		return false;
	}
	
	public ArrayList<Employee> retrieve(){
		return employeeList;
	}

	public boolean update(){
		return false;
	}

	public Employee findById(String id) {
		for(Employee employee : employeeList) {
			if(employee.getId().equals(id)) return employee;
		}
		return null;
	}

}