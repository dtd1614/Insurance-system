package repository.sale;

import domain.Pay;
import domain.Sale;

import java.util.ArrayList;

public interface SaleList {
    public int add(Sale sale);

    public boolean delete();

    public ArrayList<Sale> retrieve();

    public boolean update();
}
