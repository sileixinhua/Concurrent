//重入锁的另外一种情况，子类的同步方法调用父类的同步方法
package com.sileixinhua_09;

import java.util.concurrent.TimeUnit;

public class Main {
    synchronized void m(){
        System.out.println("m stat...");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("m end...");
    }
    public static void main(String[] args){
        TT main = new TT();
        main.m();
    }

}

class TT extends Main{
    @Override
    synchronized void m(){
        System.out.println("m child stat...");
        super.m();
        System.out.println("m child end...");
    }
}