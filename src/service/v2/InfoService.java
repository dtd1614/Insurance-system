package service.v2;

import repository.info.InfoListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InfoService extends UnicastRemoteObject implements InfoServiceIF{
    private final InfoListImpl infoList;
    public InfoService(InfoListImpl infoList) throws RemoteException {
        this.infoList = infoList;
    }
}
