package org.payload.rmi.rmiCodebase.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.codebase", "http://127.0.0.1:80/");
        LocateRegistry.createRegistry(1099);
        PhoneImpl phone = new PhoneImpl();
        Naming.bind("rmi://127.0.0.1:1099/phone", phone);
        System.out.println("registry started...");
    }
}
