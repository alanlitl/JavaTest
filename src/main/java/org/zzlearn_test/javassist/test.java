package org.zzlearn_test.javassist;
import javassist.*;

public class test {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cts = cp.get("org.learn_test.javassist.Hello");
        CtMethod mtd = cts.getDeclaredMethod("hello");
        mtd.insertAfter("{System.out.println(\"insertAfter\");}");
        Class c = cts.toClass();
        Hello h = (Hello)c.newInstance();
        h.hello();
    }
}


