package service;

import domain.Contract;
import domain.Info.Info;
import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.insurance.InsuranceStatus;
import exception.EmptyListException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ApplyInsuranceServiceIF extends Remote {
    ArrayList<Insurance> getInsuranceProductList() throws RemoteException, EmptyListException;
    int calculateMaxCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException;
    int calculateMinCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException;
    int calculatePayment(Info info, int compensation, int calculationFormulaId) throws RemoteException, NoDataException;
    int applyInsurance(Contract contract) throws RemoteException;
}
