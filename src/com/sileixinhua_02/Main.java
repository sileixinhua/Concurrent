//直接对方法加锁，直接在方法的返回值前加synchronized
package com.sileixinhua_02;

public class Main {

    public static int count =2;

    public static synchronized void main(String[] args) {
            count++;
            System.out.println(Thread.currentThread().getName()+"   count="+count);
    }
}
