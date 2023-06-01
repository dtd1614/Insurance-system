package service;

import domain.Insurance;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;
import exception.EmptyListException;
import exception.NoDataException;
import dao.InsuranceDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class InsuranceService extends UnicastRemoteObject implements InsuranceServiceIF{
    private final InsuranceDao insuranceDao;
    public InsuranceService(InsuranceDao insuranceDao) throws RemoteException {
        this.insuranceDao = insuranceDao;
    }

    @Override
    public ArrayList<Insurance> getInsuranceList() throws RemoteException, EmptyListException {
        ArrayList<Insurance> insuranceList = this.insuranceDao.retrieve();
        if(insuranceList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return insuranceList;
    }
    @Override
    public ArrayList<Insurance> getInsuranceList(InsuranceStatus insuranceStatus) throws RemoteException, EmptyListException {
        ArrayList<Insurance> insuranceList = this.insuranceDao.findByStatus(insuranceStatus);
        if(insuranceList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return insuranceList;
    }
    @Override
    public ArrayList<Insurance> getInsuranceList(InsuranceType type, InsuranceStatus status) throws RemoteException, EmptyListException {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        for(Insurance insurance : this.insuranceDao.findByStatus(status)){
            if(insurance.getType()==type) insuranceList.add(insurance);
        }
        if(insuranceList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return insuranceList;
    }
    @Override
    public Insurance getInsurance(int selectedInsuranceId) throws RemoteException, NoDataException {
        Insurance insurance = this.insuranceDao.findById(selectedInsuranceId);
        if(insurance == null) throw new NoDataException("! 존재하지 않는 보험입니다.");
        return insurance;
    }

    @Override
    public int makeInsurance(Insurance insurance) throws RemoteException  {
        return insuranceDao.add(insurance);
    }
    @Override
    public boolean examineAuthorization(int id, InsuranceStatus status) throws RemoteException, NoDataException {
        this.getInsurance(id);
        return insuranceDao.update(id, status);
    }
}
