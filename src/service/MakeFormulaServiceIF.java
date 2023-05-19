package service;

import domain.calculationFormula.CalculationFormula;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MakeFormulaServiceIF extends Remote {
    int makeFormula(CalculationFormula calculationFormula) throws RemoteException;
}
