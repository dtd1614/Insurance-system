package service;

import domain.calculationFormula.CalculationFormula;
import repository.calculation.CalculationFormulaListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MakeFormulaService extends UnicastRemoteObject implements MakeFormulaServiceIF {
    private final CalculationFormulaListImpl calculationFormulaList;

    public MakeFormulaService(CalculationFormulaListImpl calculationFormulaList) throws RemoteException {
        this.calculationFormulaList = calculationFormulaList;
    }
    @Override
    public int makeFormula(CalculationFormula calculationFormula) throws RemoteException {
        return calculationFormulaList.add(calculationFormula);
    }
}
