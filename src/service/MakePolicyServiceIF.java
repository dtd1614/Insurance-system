package service;

import domain.Insurance;
import domain.Policy;
import enumeration.insurance.InsuranceType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// 내가 해야 하는 serviceif_보상지침을 수립하다
public interface MakePolicyServiceIF extends Remote {
    int makeCompensatePolicy(Policy policy) throws RemoteException;
}

