package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReportAccidentService extends UnicastRemoteObject implements ReportAccidentServiceIF {
    public ReportAccidentService() throws RemoteException {
    }
}
