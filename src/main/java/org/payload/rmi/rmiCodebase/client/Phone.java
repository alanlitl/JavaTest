package org.payload.rmi.rmiCodebase.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Phone extends Remote {
    String sendMessage(String msg) throws RemoteException;
    Object evil() throws RemoteException;
}
