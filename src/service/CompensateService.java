package service;

import domain.Accident;
import domain.Insurance;
import enumeration.accident.AccidentStatus;
import exception.EmptyListException;
import exception.NoDataException;
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
    public ArrayList<Accident> getAccidentList(AccidentStatus accidentStatus) throws RemoteException, EmptyListException {
        ArrayList<Accident> accidentList = this.accidentList.findByStatus(accidentStatus); // 상태 가져와야
        if(accidentList.isEmpty()){
            throw new EmptyListException("보험유형이 없습니다.");
        }
        return accidentList;
    }
    @Override
    public boolean examineAccident(int id, AccidentStatus status) throws RemoteException{
        return accidentList.update(id, status);
    }
  
    @Override
    public Accident getContractId(int id) throws RemoteException, NoDataException {
        if(accidentList == null){
            throw new NoDataException("가져올 계약이 없습니다.");
        }
        return accidentList.findByContractId(id); // null 인지 아닌지 검사 null- >nodataException
    }

}
