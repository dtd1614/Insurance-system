package repository.pay;

import domain.Contract;
import domain.Pay;

import java.util.ArrayList;

public class PayListImpl implements PayList{
    private final ArrayList<Pay> payList = new ArrayList<>();
    @Override
    public int add(Pay pay) {
        if(payList.size()==0)pay.setId(1);
        else {pay.setId(payList.get(payList.size()-1).getId()+1);}
        if(payList.add(pay)) return pay.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<Pay> retrieve() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

	public Pay findByPayId(int payId) {
		for(Pay pay:this.payList) {
			if(pay.getId()==payId)
				return pay;
		}
		return null;
		
	}
}
