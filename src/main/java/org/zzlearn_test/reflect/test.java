package org.zzlearn_test.reflect;

import java.lang.reflect.*;

public class test {
    public static void main(String[] args) throws Exception {
        //Class cls = Runtime.class;
        Class cls = Class.forName("java.lang.Runtime");
        Method m1 = cls.getDeclaredMethod("getRuntime");
        Object rt = m1.invoke(cls);
        Method m2 = cls.getMethod("exec", String.class);
        m2.invoke(rt, "calc.exe");
    }

}
