package org.maxim.cis.calculator.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operation extends Remote {

    String execute(String[] args) throws RemoteException;

}
