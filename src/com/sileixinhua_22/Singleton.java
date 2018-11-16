//线程安全的单例模式
//不用加锁，还可以实现懒加载
package com.sileixinhua_22;

import java.util.Arrays;

public class Singleton {

    private Singleton(){
        System.out.println("single");
    }

    //内部类有一个静态对象s
    private static class Inner{
        private static Singleton s = new Singleton();
    }

    //这里get到的是内部类的静态对象
    private static Singleton getSingle(){
        return Inner.s;
    }

    public static synchronized void main(String[] args) {
            Thread[] ths = new Thread[200];
            for(int i=0;i<ths.length;i++){
                ths[i]= new Thread(()->{
                   Singleton.getSingle();
                });
            }

        Arrays.asList(ths).forEach(o->o.start());
    }
}
