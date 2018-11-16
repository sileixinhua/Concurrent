//LinkedBlockingQueue实现生产者消费者
//高并发的情况下可以使用两种队列，
//内部加锁的ConcurrentQueue
//还有阻塞式队列BlockingQueue
//包括LinkedBlockingQueue和ArrayBlockingQueue
package com.sileixinhua_24;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class T05_LinkedBlockingQueue {
    static BlockingDeque<String> strs = new LinkedBlockingDeque<>();

    static Random r = new Random();

    public static void main(String[] args){
        new Thread(()->{
           for(int i=0;i<100;i++){
                try{
                    //put()方法是如果满了则会等待
                    //阻塞等待消费者消费
                    strs.put("a"+i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
           }
        },"p1").start();

        for(int i =0;i<5;i++){
            new Thread(()->{
               for (;;){
                   try{
                       //与上面对应，take()如果没有则等待
                       System.out.println(Thread.currentThread().getName()+"take-"+strs.take());
                   }catch(InterruptedException e){
                       e.printStackTrace();
                   }
               }
            },"c"+i).start();
        }
    }
}
