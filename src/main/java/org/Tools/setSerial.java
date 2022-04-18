package org.Tools;

import java.io.*;

public class setSerial {
    public static byte[] serial(Object obj) throws Exception {
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(obj);
        oos.close();
        return barr.toByteArray();
    }

    public static void unserial(byte[] barr) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        Object o = (Object)ois.readObject();
    }
}
