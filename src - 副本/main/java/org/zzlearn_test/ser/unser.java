package org.zzlearn_test.ser;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class unser {
    public static void main(String[] args) throws Exception {
        // 反序列化读取 out.bin 文件
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"));
        ois.readObject();
    }
}

