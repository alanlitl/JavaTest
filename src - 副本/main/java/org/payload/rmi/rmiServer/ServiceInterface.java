package org.payload.rmi.rmiServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ServiceInterface extends Remote {
    String hello(Object a) throws RemoteException;//在客户端中也需要调用该接口，所以需要将需要实现的方法写在这里

    String hello2(String a) throws RemoteException;
}
