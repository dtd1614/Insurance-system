package service;

import domain.Info.Info;
import exception.NoDataException;
import repository.InfoList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InfoService extends UnicastRemoteObject implements InfoServiceIF{
    private final InfoList infoList;
    public InfoService(InfoList infoList) throws RemoteException {
        this.infoList = infoList;
    }
//    @Override
    public Info getInfo(int infoId) throws RemoteException, NoDataException {
        Info info = infoList.findById(infoId);
        if(info==null) throw new NoDataException("존재하지 않는 정보입니다.");
        return info;
    }

    public int makeInfo(Info info) {
        return infoList.add(info);
    }
}
