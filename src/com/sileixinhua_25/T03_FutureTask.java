//FutureTask执行未来的任务，注意这个任务有返回值
//FutureTask与Runnable做区分的，Runnable没有返回值
//Callable
package com.sileixinhua_25;

import java.util.concurrent.*;

public class T03_FutureTask {
    public static void main(String[] args)throws InterruptedException,ExecutionException{
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get());//阻塞

        //创建线程池，支持大小为5
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        //isDone()是询问任务执行完没有啊？
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());

    }
}
