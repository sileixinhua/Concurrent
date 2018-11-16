//同步方法和非同步方法是否可以同时调用
//结果为在m1的执行过程之中，m2可以被执行
//在synchronized执行之中，非synchronized可以正常运行
//理解为synchronized执行是需要那把锁的，但是非synchronized不需要申请锁
package com.sileixinhua_06;

public class Main implements Runnable{

    @Override
    public void run() {

    }

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 stat...");
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end...");
    }

    public  void m2(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2...");
    }

    public static synchronized void main(String[] args) {
            Main main = new Main();
            new Thread(()->main.m1(),"t1    ").start();
            new Thread(()->main.m2(),"t2    ").start();

            /*//下面的写法等于上面的写法，上面是lambda表达式
            new Thread(new Runnable() {
                @Override
                public void run() {
                    main.m1();
                }
            });*/
    }
}
