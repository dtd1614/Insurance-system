package dao;

import domain.Accident;
import enumeration.accident.AccidentStatus;

import java.util.ArrayList;

public class AccidentDao extends Dao {
    private final ArrayList<Accident> accidentList = new ArrayList<>();
    public int add(Accident accident) {
        if(accidentList.size()==0) accident.setId(1);
        else {accident.setId(accidentList.get(accidentList.size()-1).getId()+1);}
        if(accidentList.add(accident)) return accident.getId();
        else {return 0;}
    }
    public ArrayList<Accident> findByStatus(AccidentStatus accidentStatus) {
        ArrayList<Accident> accidentListByStatus = new ArrayList<>();
        for(Accident accident : accidentList) {
            if(accident.getStatus()== accidentStatus) accidentListByStatus.add(accident);
        }
        return accidentListByStatus;
    }
    public boolean update(int id, AccidentStatus end) {
        for(Accident accident : accidentList) {
            if(accident.getId()==id) {accident.setStatus(end); return true;}
        }
        return false;
    }

    public Accident findById(int id) {
        for(Accident accident : accidentList) {
            if(accident.getId() == id) return accident;
        }
        return null;
    }
}
