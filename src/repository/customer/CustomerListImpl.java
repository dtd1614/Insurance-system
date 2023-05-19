package repository.customer;
import java.util.ArrayList;

import domain.customer.Customer;
public class CustomerListImpl implements CustomerList {
	private final ArrayList<Customer> customerList = new ArrayList<>();

	public boolean add(Customer customer){
		return customerList.add(customer);
	}

	public boolean delete(){
		return false;
	}

	public ArrayList<Customer> retrieve(){
		return customerList;
	}

	public boolean update(){
		return false;
	}

	public Customer findById(String id) {
		for(Customer customer : customerList) {
			if(customer.getId().equals(id)) return customer;
		}
		return null;
	}

}