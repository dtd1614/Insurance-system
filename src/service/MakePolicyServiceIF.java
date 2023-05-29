package service;

import domain.Compensation;
import domain.Insurance;
import enumeration.insurance.InsuranceType;
import enumeration.policy.PolicyType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// 내가 해야 하는 serviceif_보상지침을 수립하다
public interface MakePolicyServiceIF extends Remote {
    ArrayList<Insurance> getInsuranceList(InsuranceType insuranceType) throws RemoteException;

    int makeCompensatePolicy(Insurance insurance) throws RemoteException;

    //int makeCompensatePolicy(Insurance insurance) throws RemoteException;
    ArrayList<Compensation> getCompensation(Compensation compensation) throws RemoteException;

    boolean makeCompensatePolicy(int id, PolicyType policyType) throws RemoteException;
}

