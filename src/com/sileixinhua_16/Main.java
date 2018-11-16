//锁定某一个对象o，如果o的属性发生改变则不影响锁的使用，
//但是如果o变成另外一个对象，则锁定的对象发生改变。
//应该避免将锁定的对象变成另外的对象。
package com.sileixinhua_16;

import java.util.concurrent.TimeUnit;

public class Main {

    //锁的信息的存储在堆内存里的
    Object o =new Object();
    void m(){
        synchronized (o){
            while (true){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static synchronized void main(String[] args) {
            Main main = new Main();
            new Thread(()->main.m(),"main").start();

            try{
                TimeUnit.SECONDS.sleep(3);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //创建第二个线程，这个线程应该是执行不了的
            Thread main2 = new Thread(()->main.m(),"main2");
            //但是这里锁的对象发生了改变，如果注释下面的这一行代码，可以看到只输出main
            main.o = new Object();
            main2.start();
    }
}
