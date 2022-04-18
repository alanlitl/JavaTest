package org.zzlearn_test.proxy.staticProxy;

public class Cinema implements Movie {
    RealMoive movie;
    public Cinema(RealMoive movie){
        super();
        this.movie = movie;
    }
    @Override
    public void play(){
        guanggao(true);
        movie.play();
        guanggao(false);
    }
    public void guanggao(boolean isStart){
        if(isStart){
            System.out.println("广告开始");
        }else{
            System.out.println("广告结束");
        }
    }
}
