package repository.accident;

import domain.Accident;
import domain.calculationFormula.CalculationFormula;

import java.util.ArrayList;

public interface AccidentList {
    public int add(Accident accident);

    public boolean delete();

    public ArrayList<Accident> retrieve();

    public boolean update();
}
