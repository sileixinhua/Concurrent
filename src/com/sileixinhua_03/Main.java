//对某个对象加锁
package com.sileixinhua_03;

public class Main {

    public static int count =1;

    public static void m(){
        count++;
        System.out.println(Thread.currentThread().getName()+"   count="+count);
        //如果锁住的是一个static静态对象
        //因为static静态对象没有new出来
        //所以实质上锁住的是com.sileixinhua_03.ReentrantLock1 类
    }

    public static void main(String[] args) {
        synchronized (Main.class){
            count++;
            System.out.println(Thread.currentThread().getName()+"   count="+count);
        }
    }
}
