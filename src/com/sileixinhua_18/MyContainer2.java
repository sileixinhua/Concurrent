//但是t2线程的死循环很浪费cpu，如果不死该怎么实现。这里使用wait()和notify()
//wait()会释放锁，notify()不会释放锁
package com.sileixinhua_18;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyContainer2 {

    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        final Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t2启动");
                for(int i=0;i<10;i++) {
                    if (c.size()!=5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }System.out.println("t2结束");
            //通知t1继续执行
            lock.notify();
        },"t2   ").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e1){
            e1.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock){
                //这里把lock给锁定了
                for(int i=0;i<10;i++){
                    c.add(new Object());
                    System.out.println("add "+i);

                    if(c.size()==5){
                        lock.notify();
                        //释放锁，让t2执行
                        try{
                            lock.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }

                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch(InterruptedException e2){
                        e2.printStackTrace();
                    }
                }
                System.out.println("t1结束");
            }
        },"t1   ").start();


    }
}
