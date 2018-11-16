//相对上一个问题，加入synchronized来解决保持原子性
package com.sileixinhua_13;

import java.util.List;
import java.util.ArrayList;

public class Main {
    volatile int count =0;
    synchronized void  m(){
        for(int i=0;i<10000;i++){
            count++;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<Thread> threads = new ArrayList<Thread>();

        for (int i=0;i<10;i++){
            threads.add(new Thread(()->main.m(),"thread-"+i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try{
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        System.out.println(main.count);
    }
}
