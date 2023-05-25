package service;

import java.rmi.Remote;

public interface ConcludeServiceIF extends Remote {

    int Searchapplicationlist();

    boolean conclude();
}
