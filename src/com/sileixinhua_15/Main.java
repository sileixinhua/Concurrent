//synchronized优化
//同步代码块里的语句越少越好
//比较m1和m2
package com.sileixinhua_15;

import java.util.concurrent.TimeUnit;

public class Main {

    static int count =0;

    synchronized static void m1(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        count++;
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    static void m2(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //这里只给一个方法上锁，对比m1方法上锁的粒度要细很多
        //采用细粒度的锁，可以使线程争用的时间变短，从而提高效率
        /*synchronized (this){
            count++;
        }
        */

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static synchronized void main(String[] args) {
            m1();
            m2();
    }
}
