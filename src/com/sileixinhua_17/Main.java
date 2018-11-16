//不要以字符串常量做为锁定对象
package com.sileixinhua_17;

public class Main {

    //实际锁定的使“Hello”这个字符串，不是s1,s2
    static String s1 = "Hello";
    static String s2 = "Hello";

    static void m1(){
        synchronized (s1){

        }
    }

    static void m2(){
        synchronized (s2){

        }
    }

    public static synchronized void main(String[] args) {
            m1();
            m2();
    }
}
