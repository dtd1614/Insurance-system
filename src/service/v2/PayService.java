package service.v2;

import repository.pay.PayListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PayService extends UnicastRemoteObject implements PayServiceIF{
    private final PayListImpl payList;
    protected PayService(PayListImpl payList) throws RemoteException {
        this.payList = payList;
    }
}
