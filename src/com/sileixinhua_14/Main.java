//相对上一个问题，加入AtomXXX类,原子性int类
//注意这里开始就不能用count++；这样的语句了
//需要使用count.incrementAndGet();
//incrementAndGet()是原子方法，用系统原语完成的，所以效率比synchronized高好多
package com.sileixinhua_14;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //volatile int count =0;
    AtomicInteger count = new AtomicInteger();
    /*synchronized*/ void  m(){
        for(int i=0;i<10000;i++){
            //
            //但是这里还是会有线程会插进来
            //
            count.incrementAndGet();
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
