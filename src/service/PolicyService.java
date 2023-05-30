package service;

import domain.Policy;
import repository.PolicyList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PolicyService extends UnicastRemoteObject implements PolicyServiceIF{
    private final PolicyList policyList;
    public PolicyService(PolicyList policyList) throws RemoteException {
        this.policyList = policyList;
    }
//    @Override
    public int makeCompensatePolicy(Policy policy) throws RemoteException {
        return policyList.add(policy);
    }
}
