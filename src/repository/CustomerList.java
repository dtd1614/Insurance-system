package repository;
import java.util.ArrayList;

import domain.customer.Customer;
public class CustomerList extends DBConnector{
	private final ArrayList<Customer> customerList = new ArrayList<>();

	public boolean add(Customer customer){
		return customerList.add(customer);
	}

	public ArrayList<Customer> retrieve(){
		return customerList;
	}

	public Customer findById(String id) {
		for(Customer customer : customerList) {
			if(customer.getId().equals(id)) return customer;
		}
		return null;
	}

}