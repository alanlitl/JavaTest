package org.payload.rmi.rmiServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class Service extends UnicastRemoteObject implements ServiceInterface {
    public Service() throws RemoteException {
        super();
    }
    public String hello(Object a) throws RemoteException {
        System.out.println("call from ");
        return "Hello " + a;
    }
    public String hello2(String a) throws RemoteException {
        System.out.println("call from "+a);
        return "Hello " + a;
    }
}
