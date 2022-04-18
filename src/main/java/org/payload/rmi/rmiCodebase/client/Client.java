package org.payload.rmi.rmiCodebase.client;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        System.setProperty("java.security.policy", "file:./java.policy");
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        System.setSecurityManager(new RMISecurityManager());

        Registry registry = LocateRegistry.getRegistry(1099);
        Phone phone = (Phone) registry.lookup("phone");
        System.out.println(phone.sendMessage("hello"));
    }
}
