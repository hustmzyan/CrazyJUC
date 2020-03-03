package com.mzyan._05_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by mzYan on 2020-03-02 21:27
 */
public class CallabelTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * new Thread(Runnable()).start();
         * new Thread(FutureTask<V>()).start();
         * new Thread(FutureTask<V>( Callable )).start();
         */
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread); // 适配类
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); // 结果会被缓存，效率高

        Integer o = (Integer) futureTask.get(); //获取callable的返回结果  这个方法可能会被阻塞
        // 或者使用一部通信来处理
        System.out.println(o);
    }
}


class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}