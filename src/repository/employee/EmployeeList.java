package repository.employee;
import java.util.ArrayList;

import domain.Employee;
public interface EmployeeList {

	public boolean add(Employee employee);

	public boolean delete();

	public ArrayList<Employee> retrieve();

	public boolean update();
	
	public Employee findById(String id);

}