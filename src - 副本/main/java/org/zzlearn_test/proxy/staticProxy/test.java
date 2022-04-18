package org.zzlearn_test.proxy.staticProxy;

public class test {
    public static void main(String[] args){
        RealMoive realMoive = new RealMoive();
        Movie movie = new Cinema(realMoive);
        movie.play();
    }
}
