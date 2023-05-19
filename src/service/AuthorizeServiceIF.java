package service;

import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.insurance.InsuranceStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AuthorizeServiceIF extends Remote {
    ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException;
    boolean examineAuthorization(int id, InsuranceStatus status) throws RemoteException;
    CalculationFormula getCaculationFormula(int id) throws RemoteException;
}
