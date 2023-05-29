package service;

import domain.Accident;
import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import enumeration.accident.AccidentStatus;
import enumeration.insurance.InsuranceStatus;
import exception.EmptyListException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CompensateServiceIF extends Remote {

    ArrayList<Accident> getAccidentList(enumeration.accident.AccidentStatus accidentStatus) throws RemoteException, EmptyListException;
    boolean examineAccident(int id, AccidentStatus status) throws RemoteException, EmptyListException;
    Accident getContractId(int id) throws RemoteException;
}