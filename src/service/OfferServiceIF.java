package service;

import domain.customer.Customer;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

public interface OfferServiceIF extends Remote {

    boolean Propose(String saleEmployeeId, String customerId, int insuranceId, String message) throws IOException;
//    String Show_Customer_Information();
    Customer findByCustomerId(String selectedCustomerId);
}
