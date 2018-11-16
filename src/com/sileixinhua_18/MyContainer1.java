//实现一个容器，提供两个方法add,size
//写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
//当个数到5个时，线程2给出提示并结束
//但是t2线程的死循环很浪费cpu，如果不死该怎么实现。这里使用wait()和notify()
package com.sileixinhua_18;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyContainer1 {

    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        new Thread(()->{
            for(int i=0;i<10;i++){
                c.add(new Object());
                System.out.println("add "+i);

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1   ").start();

        new Thread(()->{
            while(true){
                if (c.size()==5){
                    break;
                }
            }
            System.out.println("t2 end...");
        },"t2").start();
    }
}
