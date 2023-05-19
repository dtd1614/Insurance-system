package repository.compensation;

import domain.Compensation;

import java.util.ArrayList;

public class CompensationListImpl implements CompensationList{
    private final ArrayList<Compensation> compensationList = new ArrayList<>();
    @Override
    public int add(Compensation compensation) {
        if(compensationList.size()==0)compensation.setId(1);
        else {compensation.setId(compensationList.get(compensationList.size()-1).getId()+1);}
        if(compensationList.add(compensation)) return compensation.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<CompensationList> retrieve() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }
}
