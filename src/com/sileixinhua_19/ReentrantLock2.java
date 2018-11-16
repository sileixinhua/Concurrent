//tryLock尝试锁定
package com.sileixinhua_19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock();//等于synchronized(this)
            for(int i=0;i<10;i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            //一般在finally里释放锁 lock.unlock();
        }
    }

    //使用tryLock来尝试锁定，不管锁定与否，方法都继续执行，
    //也可以根据tryLock的返回值来判定是否需要锁定
    //也可以指定tryLock的时间，由于try Lock(time)抛出异常，所以要注意unclock的处理，必须放在finally中
    void m2(){
        boolean locked = false;
        try{
            locked = lock.tryLock(2,TimeUnit.SECONDS);
            //结果为没有锁定
            System.out.println("m2 ..."+locked);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            if (locked)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock2 k1 = new ReentrantLock2();
        new Thread(k1::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        new Thread(k1::m2).start();
    }
}
