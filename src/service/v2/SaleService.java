package service.v2;

import repository.sale.SaleListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SaleService extends UnicastRemoteObject implements SaleServiceIF {
    private final SaleListImpl saleList;
    public SaleService(SaleListImpl saleList) throws RemoteException {
        this.saleList = saleList;
    }
}
