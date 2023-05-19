package service;

import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;
import repository.calculation.CalculationFormulaListImpl;
import repository.insurance.InsuranceListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MakeInsuranceService extends UnicastRemoteObject implements MakeInsuranceServiceIF {
    private final CalculationFormulaListImpl calculationFormula;
    private final InsuranceListImpl insuranceList;
    public MakeInsuranceService(CalculationFormulaListImpl calculationFormula, InsuranceListImpl insuranceList) throws RemoteException {
        this.calculationFormula = calculationFormula;
        this.insuranceList = insuranceList;
    }
    @Override
    public ArrayList<CalculationFormula> getCalculationFormulaList(InsuranceType insuranceType) throws RemoteException  {
        return calculationFormula.findByType(insuranceType);
    }
    @Override
    public int makeInsurance(Insurance insurance) throws RemoteException  {
        return insuranceList.add(insurance);
    }
    @Override
    public ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException  {
        return insuranceList.findByStatus(insuranceStatus);
    }
}
