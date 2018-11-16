//ThreadLocal线程局部变量
//第二个线程改了名字，第一个线程启动
//第一个线程启动，第二个线程改了名字
package com.sileixinhua_21;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {

    volatile static Person p =new Person();

    public static void main(String[] args) {
            new Thread(()->{
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(p.name);
            }).start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            p.name="silei silei";
        }).start();
    }
}

class Person{
    String name = "silei";
}