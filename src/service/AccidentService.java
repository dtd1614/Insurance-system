package service;

import domain.Accident;
import enumeration.accident.AccidentStatus;
import exception.EmptyListException;
import exception.NoDataException;
import dao.AccidentDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AccidentService extends UnicastRemoteObject implements AccidentServiceIF{
    private final AccidentDao accidentDao;
    public AccidentService(AccidentDao accidentDao) throws RemoteException {
        this.accidentDao = accidentDao;
    }
    @Override
    public ArrayList<Accident> getAccidentList(AccidentStatus status) throws RemoteException, EmptyListException {
        ArrayList<Accident> accidentList = this.accidentDao.findByStatus(status);
        if(accidentList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return accidentList;
    }
    @Override
    public ArrayList<Accident> getAccidentList() throws RemoteException, EmptyListException {
        ArrayList<Accident> accidentList = this.accidentDao.retrieve();
        if(accidentList.isEmpty()) throw new EmptyListException("! 목록이 존재하지 않습니다.");
        return accidentList;
    }
    @Override
    public Accident getAccident(int id) throws RemoteException, NoDataException {
        Accident accident = this.accidentDao.findById(id);
        if(accidentDao == null) throw new NoDataException("! 존재하지 않는 사고입니다.");
        return accidentDao.findById(id);
    }
    @Override
    public int reportAccident(Accident accident) throws RemoteException {
        return this.accidentDao.add(accident);
    }
    @Override
    public boolean setStatus(int accidentId, AccidentStatus status) throws RemoteException{
        return this.accidentDao.update(accidentId, status);
    }
}
