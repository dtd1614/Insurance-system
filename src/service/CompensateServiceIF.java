package service;

import domain.Accident;
import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.accident.AccidentStatus;
import enumeration.insurance.InsuranceStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CompensateServiceIF extends Remote {

    ArrayList<Accident> getAccidentList(enumeration.accident.AccidentStatus accidentStatus) throws RemoteException;
    boolean examineAccident(int id, AccidentStatus status) throws RemoteException;
    Accident getContractId(int id) throws RemoteException;
}