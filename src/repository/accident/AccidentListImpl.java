package repository.accident;

import domain.Accident;

import java.util.ArrayList;

public class AccidentListImpl implements AccidentList{
    private final ArrayList<Accident> accidentList = new ArrayList<>();
    @Override
    public int add(Accident accident) {
        if(accidentList.size()==0) accident.setId(1);
        else {accident.setId(accidentList.get(accidentList.size()-1).getId()+1);}
        if(accidentList.add(accident)) return accident.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<Accident> retrieve() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }
}
