package service.v2;

import repository.accident.AccidentListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccidentService extends UnicastRemoteObject implements AccidentServiceIF{
    private final AccidentListImpl accidentList;
    public AccidentService(AccidentListImpl accidentList) throws RemoteException {
        this.accidentList = accidentList;
    }
}
