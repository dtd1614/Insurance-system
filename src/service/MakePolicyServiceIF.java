package service;

import domain.Insurance;
import domain.Policy;
import enumeration.insurance.InsuranceType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MakePolicyServiceIF extends Remote {
    int makeCompensatePolicy(Policy policy) throws RemoteException;
}

