package repository.accident;

import domain.Accident;
import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.accident.AccidentStatus;
import enumeration.insurance.InsuranceStatus;

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

    public Accident findByContractId(int id) {
        for(Accident accident : accidentList) {
            if(accident.getId() == id) return accident;
        }
        return null;
    }
}
