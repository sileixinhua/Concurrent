//用reentrantLock代替synchronized
//本例m1锁定this,只有m1执行完毕的时候，m2才执行
//synchronized的原始语义
//因为他们用的是同一把锁，所以m1()执行完之后执行m2()
//synchronized是自动上锁，自动释放
//reentrantLock是手动上锁，手动释放
package com.sileixinhua_19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1 {
    Lock lock = new ReentrantLock();

    void m1(){
    try{
        lock.lock();//等于synchronized(this)
        for(int i=0;i<10;i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            //一般在finally里释放锁 lock.unlock();
        }
    }

    void m2(){
        lock.lock();
        System.out.println("m2 ...");
        lock.unlock();
    }

    public static synchronized void main(String[] args) {
        ReentrantLock1 k1 = new ReentrantLock1();
        new Thread(()->k1.m1()).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        new Thread(()->k1.m2()).start();
    }
}
