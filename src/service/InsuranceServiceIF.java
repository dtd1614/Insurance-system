package service;

import domain.Insurance;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;
import exception.EmptyListException;
import exception.NoDataException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InsuranceServiceIF  extends Remote {
    ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException, EmptyListException;

    ArrayList<Insurance> getInsuranceList(InsuranceType type, InsuranceStatus status) throws RemoteException, EmptyListException;

    Insurance getInsurance(int selectedInsuranceId) throws RemoteException, NoDataException;

    ArrayList<Insurance> getInsuranceList() throws RemoteException, EmptyListException;

    int makeInsurance(Insurance insurance) throws RemoteException;

    boolean examineAuthorization(int id, InsuranceStatus status) throws RemoteException, NoDataException;
}
