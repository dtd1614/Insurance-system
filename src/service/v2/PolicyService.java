package service.v2;

import repository.policy.PolicyListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PolicyService extends UnicastRemoteObject implements PolicyServiceIF{
    private final PolicyListImpl policyList;
    public PolicyService(PolicyListImpl policyList) throws RemoteException {
        this.policyList = policyList;
    }
}
