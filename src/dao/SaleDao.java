package dao;

import domain.Sale;

import java.util.ArrayList;

public class SaleDao extends Dao {
    private final ArrayList<Sale> saleList = new ArrayList<>();
    public int add(Sale sale) {
        if(saleList.size()==0) sale.setId(1);
        else {sale.setId(saleList.get(saleList.size()-1).getId()+1);}
        if(saleList.add(sale)) return sale.getId();
        else {return 0;}
    }

    public ArrayList<Sale> findByCustomerId(String customerId) {
        ArrayList<Sale> saleList = new ArrayList<>();
        for(Sale sale : this.saleList){
            if(customerId.equals(sale.getCustomerId()))saleList.add(sale);
        }
        return saleList;
    }
}
