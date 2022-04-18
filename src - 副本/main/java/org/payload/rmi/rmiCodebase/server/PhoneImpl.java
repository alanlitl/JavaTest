package org.payload.rmi.rmiCodebase.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PhoneImpl extends UnicastRemoteObject implements Phone{
    protected PhoneImpl() throws RemoteException {
    }

    @Override
    public String sendMessage(String msg) throws RemoteException {
        System.out.println("sendMessage was invoked by client");
        return msg;
    }
    public Object evil(){
        return new Resp("hello from server");
    }
}
