//对某个对象加锁
package com.sileixinhua_00;

public class Main {

    public static int count =0;
    public static Object o=new Object();

    public static void main(String[] args) {
        synchronized (o){
            count++;
            System.out.println(Thread.currentThread().getName()+"   count="+count);
        }
    }
}
