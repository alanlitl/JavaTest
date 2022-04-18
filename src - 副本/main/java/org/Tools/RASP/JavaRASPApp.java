package org.Tools.RASP;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;


public class JavaRASPApp {
    public static void premain(String agentArgs, Instrumentation instru) throws ClassNotFoundException, UnmodifiableClassException {
        System.out.println("start agentArgs : " + agentArgs);
        instru.addTransformer(new ClassTransFormer());
    }

}

