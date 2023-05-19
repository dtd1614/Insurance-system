package repository.sale;

import domain.Sale;

import java.util.ArrayList;

public class SaleListImpl implements SaleList{
    private final ArrayList<Sale> saleList = new ArrayList<>();
    @Override
    public int add(Sale sale) {
        if(saleList.size()==0) sale.setId(1);
        else {sale.setId(saleList.get(saleList.size()-1).getId()+1);}
        if(saleList.add(sale)) return sale.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<Sale> retrieve() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }
}
