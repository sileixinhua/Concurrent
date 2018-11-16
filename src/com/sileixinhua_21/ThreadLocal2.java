//ThreadLocal是使用空间换时间，synchronized是时间换空间
//比如hibernate中session就存储在ThreadLocal中，避免使用synchronized
//ThreadLocal可能会导致内存泄漏
//下面程序运行结果为null，是因为ThreadLocal把变量在每个内存中都拷贝了一份
//所以容易导致内存泄漏，还有空间换时间
package com.sileixinhua_21;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {

    //volatile static Person p =new Person();

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            tl.set(new Person());
        }).start();
    }

    static class Person{
        String name = "silei";
    }
}

