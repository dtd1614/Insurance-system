package repository.pay;

import domain.Insurance;
import domain.Pay;

import java.util.ArrayList;

public interface PayList {
    public int add(Pay pay);

    public boolean delete();

    public ArrayList<Pay> retrieve();

    public boolean update();
}
