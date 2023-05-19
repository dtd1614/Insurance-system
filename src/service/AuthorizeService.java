package service;

import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.insurance.InsuranceStatus;
import repository.calculation.CalculationFormulaListImpl;
import repository.insurance.InsuranceListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AuthorizeService extends UnicastRemoteObject implements AuthorizeServiceIF {
    private final InsuranceListImpl insuranceList;
    private final CalculationFormulaListImpl calculationFormulaList;

    public AuthorizeService(InsuranceListImpl insuranceList, CalculationFormulaListImpl calculationFormulaList) throws RemoteException {
        this.insuranceList = insuranceList;
        this.calculationFormulaList = calculationFormulaList;
    }

    @Override
    public ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException  {
        return insuranceList.findByStatus(insuranceStatus);
    }
    @Override
    public boolean examineAuthorization(int id, InsuranceStatus status) throws RemoteException  {
        return insuranceList.update(id, status);
    }
    @Override
    public CalculationFormula getCaculationFormula(int id) throws RemoteException  {
        return calculationFormulaList.findById(id);
    }
}
