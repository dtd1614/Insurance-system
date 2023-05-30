package repository;

import domain.Sale;

import java.util.ArrayList;

public class SaleList extends DBConnector{
    private final ArrayList<Sale> saleList = new ArrayList<>();
    public int add(Sale sale) {
        if(saleList.size()==0) sale.setId(1);
        else {sale.setId(saleList.get(saleList.size()-1).getId()+1);}
        if(saleList.add(sale)) return sale.getId();
        else {return 0;}
    }
}
