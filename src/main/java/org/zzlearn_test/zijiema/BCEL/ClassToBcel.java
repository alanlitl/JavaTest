package org.zzlearn_test.zijiema.BCEL;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.Repository;

import java.io.IOException;

public class ClassToBcel {
    public static void main(String []args) throws Exception {
        encode1();
        decode();
    }
    public static void encode1() throws IOException {
        JavaClass cls = Repository.lookupClass(org.zzlearn_test.zijiema.BCEL.test.class);
        String code = Utility.encode(cls.getBytes(), true);
        System.out.println("$$BCEL$$" + code);
    }
    public static void decode(){
        //new ClassLoader().loadClass("").newInstance();
    }
}
