package service;

import domain.Contract;
import domain.Insurance;

import java.rmi.Remote;

public interface ConcludeServiceIF extends Remote {
//
//    int Searchapplicationlist();
//
    boolean conclude(int selectedId);

    Contract findByContractId(int selectedContractId);

    Insurance findByInsuranceId(int selectedInsuranceId);
}
