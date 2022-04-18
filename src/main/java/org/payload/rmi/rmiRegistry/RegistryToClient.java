package org.payload.rmi.rmiRegistry;

import org.payload.rmi.rmiServer.Service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryToClient {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        //registry.rebind("http://127.0.0.1/Hello", new Service());
        registry.unbind("http://127.0.0.1/Hello");
    }
}