//Vector同步容器，其中所有的方法都是加锁的
//但是出现的问题和上一个一样，最后一张票有多个线程抢
package com.sileixinhua_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TickerSeller2{

    //static List<String> tickets = new ArrayList<>();
    static Vector<String> tickets = new Vector<>();

    static {
        for(int i=0;i<1000;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        //启动十个线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(tickets.size()>0){

                    try{
                        TimeUnit.MILLISECONDS.sleep(10);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    System.out.println("销售了--"+tickets.remove(0));
                }
            }).start();
        }
    }
}
