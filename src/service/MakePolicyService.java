package service;

import domain.Insurance;
import domain.Policy;
import enumeration.insurance.InsuranceType;
import repository.insurance.InsuranceListImpl;
import repository.policy.PolicyListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MakePolicyService extends UnicastRemoteObject implements MakePolicyServiceIF {
    private final PolicyListImpl policyList;
    protected MakePolicyService(PolicyListImpl policyList) throws RemoteException {
        this.policyList = policyList;
    }

    @Override
    public int makeCompensatePolicy(Policy policy) throws RemoteException {
        return policyList.add(policy);
    }
}
