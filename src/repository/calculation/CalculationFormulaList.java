package repository.calculation;

import java.util.ArrayList;

import domain.calculationFormula.CalculationFormula;


public interface CalculationFormulaList {

	public int add(CalculationFormula calculationFormula);

	public boolean delete();

	public ArrayList<CalculationFormula> retrieve();

	public boolean update();

}