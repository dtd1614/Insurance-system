package service;

import domain.Compensation;
import domain.Insurance;
import enumeration.insurance.InsuranceType;
import enumeration.policy.PolicyType;
import repository.insurance.InsuranceListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MakePolicyService extends UnicastRemoteObject implements MakePolicyServiceIF {
    private final InsuranceListImpl insuranceTypeList;
    //private final CompensationListImpl CompensationList;

    protected MakePolicyService(InsuranceListImpl insuranceTypeList) throws RemoteException {
        this.insuranceTypeList = insuranceTypeList;
    }

    @Override
    public ArrayList<Insurance> getInsuranceList(InsuranceType insuranceType) throws RemoteException {
        return insuranceTypeList.findByType(insuranceType);
    }

    @Override
    public int makeCompensatePolicy(Insurance insurance) throws RemoteException {
        return insuranceTypeList.add(insurance);
    }

    @Override
    public ArrayList<Compensation> getCompensation(Compensation compensation) throws RemoteException {
        //return CompensationListImpl.add(compensation);
        return null;
    }

    @Override
    public boolean makeCompensatePolicy(int id, PolicyType policyType) {
        return false;
    }
}
