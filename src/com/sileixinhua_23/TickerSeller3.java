package com.sileixinhua_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TickerSeller3{

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
                while(true){
                    //锁定tickets，每次都上锁
                    //把判断和操作加到一个原子操作里面去
                    synchronized (tickets) {
                        if (tickets.size() <= 0) {
                            break;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
