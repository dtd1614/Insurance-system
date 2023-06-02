package dao;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Customer;

public class CustomerDao extends Dao {

	public CustomerDao() throws Exception {
		super();
	}
	public boolean create(Customer customer) {
		String query ="insert into customer values ("+
				"'"+customer.getId()+"'"+", "+
				"'"+customer.getPassword()+"'"+", "+
				"'"+customer.getName()+"'"+", "+
				"'"+customer.getEmail()+"'"+", "+
				"'"+customer.getPhoneNumber()+"'"+", "+
				"'"+customer.getAddress()+"'"+", "+
				customer.isHasHome()+", "+
				customer.isHasWorkplace()+");";
		return create(query);
	}
	public ArrayList<Customer> retrieve(){
		ArrayList<Customer> customerList = new ArrayList<>();
		String query = "select * from customer;";
		resultSet = retrieve(query);
		try {
			while(resultSet.next()) {
				Customer customer = new Customer(resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getInt(5),
						resultSet.getString(6),
						resultSet.getBoolean(7),
						resultSet.getBoolean(8));
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return customerList;
	}
	public boolean updateHasHome(String customerId, boolean hasHome) {
		String query = "update customer set hasHome = "+hasHome+" where id = '"+customerId+"';";
		return update(query);
	}
	public boolean updateHasWorkplace(String customerId, boolean hasWorkplace) {
		String query = "update customer set hasWorkplace = "+hasWorkplace+" where id = "+"'"+customerId+"'"+";";
		return update(query);
	}
	public boolean add(Customer customer){
		return create(customer);
	}

	public Customer findById(String id) {
		for(Customer customer : retrieve()) {
			if(customer.getId().equals(id)) return customer;
		}
		return null;
	}
}