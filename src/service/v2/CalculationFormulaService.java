package service.v2;

import repository.calculation.CalculationFormulaListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculationFormulaService extends UnicastRemoteObject implements CalculationFormulaServiceIF{
    private final CalculationFormulaListImpl calculationFormulaList;
    public CalculationFormulaService(CalculationFormulaListImpl calculationFormulaList) throws RemoteException {
        this.calculationFormulaList = calculationFormulaList;
    }
}
