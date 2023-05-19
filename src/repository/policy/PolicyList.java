package repository.policy;

import domain.Pay;
import domain.Policy;

import java.util.ArrayList;

public interface PolicyList {
    public int add(Policy policy);

    public boolean delete();

    public ArrayList<Policy> retrieve();

    public boolean update();
}
