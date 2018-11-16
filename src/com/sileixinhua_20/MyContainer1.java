//实现 生产者 消费者
//写一个固定容量的同步容器，拥有get和put方法，以及getCount方法
//能够支持2个生产者和10个消费者的阻塞调用
//使用notify/notifyAll和wait来实现
package com.sileixinhua_20;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    //消费者线程
    public synchronized void put(T t){
        //这里用while不是if，因为要回while再检查一遍
        //防止别的进程插入进来
        while(lists.size()==MAX){
            try{
                //wait一般都与while一起使用
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        lists.add(t);
        count++;
        //这里用notifyAll不用notify是防止叫醒p
        this.notifyAll();
    }

    //生产者线程
    public synchronized T get(){
        T t= null;
        while(lists.size()==0){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        t =lists.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c=new MyContainer1<>();
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
