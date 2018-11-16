//对比上一个
package com.sileixinhua_05;

public class Main implements Runnable {

    public int count =1;

    public synchronized void run(){
        count++;
        System.out.println(Thread.currentThread().getName()+"   count="+count);
    }

    public static void main(String[] args) {
        Main main =new Main();
        for(int i=0;i<5;i++){
            new Thread(main,"THREAD"+i).start();
        }
    }
}