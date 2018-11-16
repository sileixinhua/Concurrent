//volatile线程之间变量可见
package com.sileixinhua_11;

import java.util.concurrent.TimeUnit;

public class Main {
    volatile boolean running = true;
    void m(){
        System.out.println("m start...");
        while(running){
            System.out.println("m doing...");
        }
        System.out.println("m end...");
    }

    public static synchronized void main(String[] args) {
            Main main=new Main();

            new Thread(()->main.m(),"t1 ").start();

            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            main.running=false;
    }
}
