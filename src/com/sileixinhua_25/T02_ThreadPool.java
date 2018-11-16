//线程池,下面程序结果如下所示
//线程池就是自己设定多少个线程，一次执行设定次数，结束了就给下一个线程，所以并发性高，效果好
//[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
//[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
//pool size是线程池的大小
//active threads是启动的线程
//queued tasks排队的任务，等待的任务放在任务队列里，实现的方式是blockQueue
//completed tasks
//service.shutdown();是线程池关闭，是所有任务都结束了之后关闭
//service.shutdownNow();是现在就强制关闭线程池
package com.sileixinhua_25;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T02_ThreadPool{
    public static void main(String[] args) throws InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=0;i<6;i++){
            service.execute(()->{
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        service.shutdown();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
