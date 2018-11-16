//对某个对象加锁
//用this方法
package com.sileixinhua_01;

public class Main {

    public static int count =0;

    public void main(String[] args) {
        //这里this要求main方法不能被定义为static
        synchronized (this){
            count++;
            System.out.println(Thread.currentThread().getName()+"   count="+count);
        }
    }
}
