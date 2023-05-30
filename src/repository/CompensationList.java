package repository;

import domain.Compensation;

import java.util.ArrayList;

public class CompensationList extends DBConnector{
    private final ArrayList<Compensation> compensationList = new ArrayList<>();
    public int add(Compensation compensation) {
        if(compensationList.size()==0)compensation.setId(1);
        else {compensation.setId(compensationList.get(compensationList.size()-1).getId()+1);}
        if(compensationList.add(compensation)) return compensation.getId();
        else {return 0;}
    }

    public ArrayList<Compensation> retrieve() {
        return compensationList;
    }

}
