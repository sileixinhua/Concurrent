package com.sileixinhua_24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_synchronizedList {
    public static void  main(String[] args){
        List<String> strs = new ArrayList<>();
        List<String> synstrs =  Collections.synchronizedList(strs);

        //这里第一个strs是没有锁的，因为是ArrayList
        //第二个是用集合的Collections.synchronizedList方法给strs上锁并复制给synstrs
    }
}
