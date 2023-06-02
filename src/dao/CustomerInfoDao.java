package dao;

import domain.Customer;
import domain.Info.CustomerInfo;

import java.util.ArrayList;

public class CustomerInfoDao extends Dao {
    private final ArrayList<CustomerInfo> customerInfoList = new ArrayList<>();
    public int add(CustomerInfo customerInfo) {
        if(customerInfoList.size()==0) customerInfo.setId(1);
        else {customerInfo.setId(customerInfoList.get(customerInfoList.size()-1).getId()+1);}
        if(customerInfoList.add(customerInfo)) return customerInfo.getId();
        else {return 0;}
    }
    public ArrayList<CustomerInfo> retrieve() {
        for(CustomerInfo customerInfo : this.customerInfoList){
            customerInfoList.add(customerInfo);
        }
        return customerInfoList;
    }
    public CustomerInfo findById(int infoId) {
        for(CustomerInfo customerInfo : customerInfoList) {
            if(customerInfo.getId() == infoId) return customerInfo;
        }
        return null;
    }
}
