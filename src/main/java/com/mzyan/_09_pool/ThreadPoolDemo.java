package com.mzyan._09_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 工具类、3 大方法，不建议使用!!!
 * 使用了线程池之后，使用线程池来创建线程
 * Created by mzYan on 2020-03-03 15:55
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单一线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 创建一个固定的线程池的大小
        ExecutorService threadPool = Executors.newCachedThreadPool();// 可伸缩的，遇强则强，遇弱则弱

        try {
            for (int i = 0; i < 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // 程序结束，需要关闭线程池
            threadPool.shutdown();
        }
    }
}
