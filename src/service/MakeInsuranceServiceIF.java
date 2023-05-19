package service;

import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MakeInsuranceServiceIF extends Remote {

    ArrayList<CalculationFormula> getCalculationFormulaList(InsuranceType insuranceType) throws RemoteException;
    int makeInsurance(Insurance insurance) throws RemoteException;
    ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException;


}
