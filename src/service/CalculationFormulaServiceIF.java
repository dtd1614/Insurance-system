package service;

import domain.customerInfo.CustomerInfo;
import domain.calculationFormula.CalculationFormula;
import enumeration.insurance.InsuranceType;
import exception.EmptyListException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CalculationFormulaServiceIF  extends Remote {

    ArrayList<CalculationFormula> getCalculationFormulaList(InsuranceType insuranceType) throws RemoteException, EmptyListException;

    CalculationFormula getCalculationFormula(int id) throws RemoteException, NoDataException;

    int makeFormula(CalculationFormula calculationFormula) throws RemoteException;

    int calculateMaxCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException;

    int calculateMinCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException;

    int calculatePayment(CustomerInfo customerInfo, int compensation, int calculationFormulaId) throws RemoteException, NoDataException;
}
