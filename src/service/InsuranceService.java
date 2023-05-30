package service;

import domain.Insurance;
import enumeration.insurance.InsuranceStatus;
import exception.EmptyListException;
import exception.NoDataException;
import repository.InsuranceList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class InsuranceService extends UnicastRemoteObject implements InsuranceServiceIF{
    private final InsuranceList insuranceList;
    public InsuranceService(InsuranceList insuranceList) throws RemoteException {
        this.insuranceList = insuranceList;
    }
//    @Override
    public ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException, EmptyListException {
        ArrayList<Insurance> insuranceList = this.insuranceList.findByStatus(insuranceStatus);
        if(insuranceList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return insuranceList;
    }
//    @Override
    public Insurance getInsurance(int selectedInsuranceId) throws RemoteException, NoDataException {
        Insurance insurance = this.insuranceList.findById(selectedInsuranceId);
        if(insurance == null) throw new NoDataException("존재하지 않는 보험입니다.");
        return insurance;
    }
//    @Override
    public ArrayList<Insurance> getAuthorizationResultList() throws RemoteException, EmptyListException {
        ArrayList<Insurance> authorizeInsuranceList = this.insuranceList.findByStatus(InsuranceStatus.Authorize);
        ArrayList<Insurance> refuseAuthorizeInsuranceList = this.insuranceList.findByStatus(InsuranceStatus.RefuseAuthorize);
        ArrayList<Insurance> underAuthorizeInsuranceList = this.insuranceList.findByStatus(InsuranceStatus.UnderAuthorize);
        ArrayList<Insurance> authorizationResultList = new ArrayList<>();
        authorizationResultList.addAll(authorizeInsuranceList);
        authorizationResultList.addAll(refuseAuthorizeInsuranceList);
        authorizationResultList.addAll(underAuthorizeInsuranceList);
        if(authorizationResultList.isEmpty()) throw new EmptyListException("목록이 존재하지 않습니다.");
        return authorizationResultList;
    }

//    @Override
    public int makeInsurance(Insurance insurance) throws RemoteException  {
        return insuranceList.add(insurance);
    }
//    @Override
    public boolean examineAuthorization(int id, InsuranceStatus status) throws RemoteException, NoDataException {
        this.getInsurance(id);
        return insuranceList.update(id, status);
    }
}
