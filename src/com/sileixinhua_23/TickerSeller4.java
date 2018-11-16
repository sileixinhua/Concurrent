package com.sileixinhua_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class TickerSeller4{

    //static List<String> tickets = new ArrayList<>();
    //static Vector<String> tickets = new Vector<>();
    //并发容器,并发的linked队列
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for(int i=0;i<1000;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        //启动十个线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                    //poll()为往外拿一个数据
                    //如果最后一张票，则poll()为null给s,break结束
                    //这里只有poll是具有原子性的
                    String s = tickets.poll();
                    if(s==null){
                        break;
                    } else{
                        System.out.println("销售了--"+s);
                    }

                }
            }).start();
        }
    }
}
