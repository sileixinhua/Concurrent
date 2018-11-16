//MyExecutor
package com.sileixinhua_25;

public class T01_MyExecutor {

    public static void main(String[] args) {
            new T01_MyExecutor().execute(()->System.out.println("hello executor"));
    }

    public void execute(Runnable command){
        //新启一个线程来执行command
        //new Thread(command).run();
        command.run();
    }
}

//execute()用来执行某一任务，里面只有这一个方法
//executorService()是一个服务，在后台不断运行，等往里面扔任务
//Callable 约等于 Runnable,区别的为前者有返回值，后者没有返回值
//executors 是用来操作上面的接口的
