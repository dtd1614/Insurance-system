package dao;

import java.util.ArrayList;

import domain.Compensation;
import domain.calculationFormula.CalculationFormula;
import domain.calculationFormula.HomeFormula;
import domain.calculationFormula.WorkplaceFormula;
import enumeration.insurance.InsuranceType;


public class CalculationFormulaDao extends Dao {
	private final ArrayList<CalculationFormula> calculationFormulaList = new ArrayList<>();
	public int add(CalculationFormula calculationFormula) {
		if(calculationFormulaList.size()==0) calculationFormula.setId(1);
		else {calculationFormula.setId(calculationFormulaList.get(calculationFormulaList.size()-1).getId()+1);}
		if(calculationFormulaList.add(calculationFormula)) return calculationFormula.getId();
		else {return 0;}
	}
	public ArrayList<CalculationFormula> retrieve(){
		ArrayList<CalculationFormula> calculationFormulaList = new ArrayList<>();
		for(CalculationFormula calculationFormula : this.calculationFormulaList){
			calculationFormulaList.add(calculationFormula);
		}
		return calculationFormulaList;
	}
	public CalculationFormula findById(int id) {
		for(CalculationFormula calculationformula : calculationFormulaList) {
			if(calculationformula.getId() == id) return calculationformula;
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