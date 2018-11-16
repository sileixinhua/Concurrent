package com.sileixinhua_24;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class T04_concurrentQueue {
    public static void main(String[] args){
        Queue<String> strs = new ConcurrentLinkedDeque<>();

        for(int i=0;i<10;i++){
            strs.offer("a"+i);
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());
    }
}
