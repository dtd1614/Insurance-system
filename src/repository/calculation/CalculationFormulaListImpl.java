package repository.calculation;

import java.util.ArrayList;

import domain.calculationFormula.CalculationFormula;
import domain.calculationFormula.HomeFormula;
import domain.calculationFormula.WorkplaceFormula;
import enumeration.insurance.InsuranceType;


public class CalculationFormulaListImpl implements CalculationFormulaList {

	private final ArrayList<CalculationFormula> calculationFormulaList = new ArrayList<>();

	public int add(CalculationFormula calculationFormula) {
		if(calculationFormulaList.size()==0) calculationFormula.setId(1);
		else {calculationFormula.setId(calculationFormulaList.get(calculationFormulaList.size()-1).getId()+1);}
		if(calculationFormulaList.add(calculationFormula)) return calculationFormula.getId();
		else {return 0;}
	}
	public boolean delete(){
		return false;
	}

	public ArrayList<CalculationFormula> retrieve(){
		return calculationFormulaList;
	}

	public boolean update(){
		return false;
	}

	
	public CalculationFormula findById(int id) {
		for(CalculationFormula caculationformula : calculationFormulaList) {
			if(caculationformula.getId() == id) return caculationformula;
		}
		return null;
	}
	
	public ArrayList<CalculationFormula> findByType(InsuranceType insuranceType) {
		ArrayList<CalculationFormula> formulaListByType = new ArrayList<>();
		for(CalculationFormula calculationFormula : calculationFormulaList) {
			if(insuranceType==InsuranceType.HomeFire) {
				if(calculationFormula instanceof HomeFormula) {formulaListByType.add(calculationFormula);}}
			if(insuranceType==InsuranceType.WorkplaceFire) {
				if(calculationFormula instanceof WorkplaceFormula) {formulaListByType.add(calculationFormula);}}
		}
		return formulaListByType;
	}
}