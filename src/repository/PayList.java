package repository;

import domain.Pay;

import java.util.ArrayList;

public class PayList extends DBConnector{
    private final ArrayList<Pay> payList = new ArrayList<>();
    public int add(Pay pay) {
        if(payList.size()==0)pay.setId(1);
        else {pay.setId(payList.get(payList.size()-1).getId()+1);}
        if(payList.add(pay)) return pay.getId();
        else {return 0;}
    }
	public Pay findById(int payId) {
		for(Pay pay:this.payList) {
			if(pay.getId()==payId)
				return pay;
		}
		return null;
	}
}
