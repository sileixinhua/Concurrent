//CopyOnWriteList写时复制容器
//多线程环境下，写的效率低，但是读的效率高
//适合写少读多的情况
package com.sileixinhua_24;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
    public static void main(String[] args){
        List<String> lists=
                //new ArrayList<>();//这个并发会出问题，因为没有锁
                //new Vector<>();//这个没有问题，结果是10000，因为有锁
                new CopyOnWriteArrayList<>();//读的时候不用加锁
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for(int i=0;i<ths.length;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        lists.add("aa"+r.nextInt(10000));
                    }
                }
            };
            ths[i]=new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths){
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
    }
}
