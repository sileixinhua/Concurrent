//分析程序的输出
//线程重入，获得的值有时候会相同
//synchronized 是原子操作，不可分，在未执行完之前不可能被打断
package com.sileixinhua_04;

public class Main implements Runnable {

    public int count =1;

    public /*synchronized*/ void run(){
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
