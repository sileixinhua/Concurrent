//上面问题最简单的方式是使用门闩Latch
//使用await和countDown方法代替wait和notify
//countDownLatch不涉及锁定，当countDown为0时当前线程继续运行
//当不涉及同步，只涉及线程之间通信的时候，wait和notify就显得太重了
//这个时候应该考虑countDownLatch/cyclicbarrier/semaphore
package com.sileixinhua_18;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer3 {

    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(()->{
                System.out.println("t2启动");
                if (c.size()!=5) {
                    try {
                        latch.await();
                        //也可以指定等待时间
                        //latch.await(500，TimeUtil.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }System.out.println("t2结束");
        },"t2   ").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e1){
            e1.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1启动");
                //这里把lock给锁定了
                for(int i=0;i<10;i++){
                    c.add(new Object());
                    System.out.println("add "+i);

                    if(c.size()==5) {
                        //打开门闩，让t2得以执行
                        //t1会继续执行，因为没有锁住任何东西
                        latch.countDown();
                    }

                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch(InterruptedException e2){
                        e2.printStackTrace();
                    }
                }
        },"t1   ").start();
    }
}
