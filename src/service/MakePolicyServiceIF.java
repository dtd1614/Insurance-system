package service;

import domain.Compensation;
import domain.Insurance;
import domain.Policy;
import enumeration.insurance.InsuranceType;
import enumeration.policy.PolicyType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MakePolicyServiceIF extends Remote {
    int makeCompensatePolicy(Policy policy) throws RemoteException;
    //ArrayList<Compensation> getCompensation(Compensation compensation) throws RemoteException;
    //boolean makeCompensatePolicy(int id, PolicyType policyType) throws RemoteException;
    //ArrayList<Policy> getInsuranceList(Policy insuranceType) throws RemoteException;
}

