//与上题相关
//使用lock和Condition来实现
//对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
package com.sileixinhua_20;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    //消费者线程
    public void put(T t){
        try{
            lock.lock();
            //如果列表满了，生产者都等着
            while(lists.size()==MAX){
                producer.await();
            }

            lists.add(t);
            count++;
            //叫醒所有的消费者
            consumer.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    //生产者线程
    public T get(){
        T t= null;
        try{
            lock.lock();
            //如果列表为空，消费者都等着
            while(lists.size()==0){
                consumer.await();
            }
            t=lists.removeFirst();
            count--;
            //生产者全部叫醒
            producer.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c=new MyContainer2<>();
        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<5;j++){
                    System.out.println(c.get());
                }
            },"消费 "+i).start();
        }

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<25;j++){
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"生产   "+i).start();
        }
    }
}
