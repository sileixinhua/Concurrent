//volatile只能保证可见性，并没有synchronized的功能
package com.sileixinhua_12;

import java.util.List;
import java.util.ArrayList;

public class Main {
    volatile int count =0;
    void m(){
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
