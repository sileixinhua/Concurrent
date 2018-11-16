//对业务写方法加锁
//对业务读方法不加锁
//容易引起脏读的问题（dirtyRead）
package com.sileixinhua_07;

import java.util.concurrent.TimeUnit;

public class Main {

    String name;
    double balance;

    public synchronized void set(String name ,double balance){
        this.name=name;
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.balance=balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static synchronized void main(String[] args) {
        Main main = new Main();
        new Thread(()->main.set("silei",100)).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(main.getBalance("silei"));

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(main.getBalance("silei"));
    }
}
