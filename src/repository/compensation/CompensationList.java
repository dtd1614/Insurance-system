package repository.compensation;

import domain.Compensation;
import domain.calculationFormula.CalculationFormula;

import java.util.ArrayList;

public interface CompensationList {
    public int add(Compensation compensation);

    public boolean delete();

    public ArrayList<CompensationList> retrieve();

    public boolean update();
}
