//程序在运行当中，如果出现异常，默认是释放锁，所以要小心发生不一致的问题。
//比如多个线程处理中，其中一个线程如果出现异常，则其他线程会进入同步代码区，可能会影响到访问异常产生的数据。
package com.sileixinhua_10;

import java.util.concurrent.TimeUnit;

public class Main {
    int count =0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+"start...");
        while(true){
            count++;
            System.out.println(Thread.currentThread().getName()+"count ="+count);
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        if(count==5){
            int i = 1/0;
        }//这里将会出现异常并释放锁，要想不释放锁，需要catch住
        }
    }

    public static void main(String[] args){
        Main main = new Main();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                main.m();
            }
        };
        new Thread(r,"t1    ").start();

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(r,"t2    ").start();
    }
}
