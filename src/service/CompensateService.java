package service;

import domain.Accident;
import domain.Insurance;
import enumeration.accident.AccidentStatus;
import repository.accident.AccidentListImpl;
import repository.calculation.CalculationFormulaListImpl;
import repository.compensation.CompensationListImpl;
import repository.customer.CustomerListImpl;
import repository.employee.EmployeeListImpl;
import repository.insurance.InsuranceListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CompensateService extends UnicastRemoteObject implements CompensateServiceIF {

    private final CompensationListImpl compensationList;
    private final AccidentListImpl accidentList;

    public CompensateService(CompensationListImpl compensationList, AccidentListImpl accidentList) throws RemoteException {
        this.compensationList = compensationList;
        this.accidentList = accidentList;
    }

    @Override
    public ArrayList<Accident> getAccidentList(AccidentStatus accidentStatus) throws RemoteException {
        return accidentList.findByAccident(accidentStatus);
    }

    @Override
    public boolean examineAccident(int id, AccidentStatus status) throws RemoteException {
        return accidentList.update(id, status);
    }

    @Override
    public Accident getContractId(int id) throws RemoteException {
        return accidentList.findByContractId(id);
    }
}
