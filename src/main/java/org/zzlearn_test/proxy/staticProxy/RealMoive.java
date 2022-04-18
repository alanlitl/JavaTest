package org.zzlearn_test.proxy.staticProxy;

public class RealMoive implements Movie {
    @Override
    public void play(){
        System.out.println("播放");
    }
}
