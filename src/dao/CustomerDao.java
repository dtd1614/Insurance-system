package dao;
import java.util.ArrayList;

import domain.Compensation;
import domain.Customer;
public class CustomerDao extends Dao {
	private final ArrayList<Customer> customerList = new ArrayList<>();

	public boolean add(Customer customer){
		return customerList.add(customer);
	}

	public ArrayList<Customer> retrieve(){
		ArrayList<Customer> customerList = new ArrayList<>();
		for(Customer customer : this.customerList){
			customerList.add(customer);
		}
		return customerList;
	}

	public Customer findById(String id) {
		for(Customer customer : customerList) {
			if(customer.getId().equals(id)) return customer;
		}
		return null;
	}

}