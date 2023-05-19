package repository.customer;

import domain.customer.Customer;

import java.util.ArrayList;

public interface CustomerList {
    public boolean add(Customer customer);

    public boolean delete();

    public ArrayList<Customer> retrieve();

    public boolean update();
}
