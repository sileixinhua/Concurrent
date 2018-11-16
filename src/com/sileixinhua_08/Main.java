//在一个同步方法中加载另一个带锁的方法是可以的。
//也就是说synchronized是可重入的。
//synchronized本身就是支持重入锁的。
package com.sileixinhua_08;

import java.util.concurrent.TimeUnit;

public class Main {
    static synchronized void m1(){
        System.out.println("m1 stat...");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        m2();
    }

    static synchronized void m2(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    public static void main(String[] args){

        m1();
    }
}
