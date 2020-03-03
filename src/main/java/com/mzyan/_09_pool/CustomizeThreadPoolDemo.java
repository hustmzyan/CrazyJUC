package com.mzyan._09_pool;

import java.util.concurrent.*;

/**
 * 自定义线程池，四种拒绝策略!!!
 * 线程池的最大线程数设置策略!!!
 * Created by mzYan on 2020-03-03 16:14
 */
public class CustomizeThreadPoolDemo {
    public static void main(String[] args) {
        // 自定义线程池!工作 ThreadPoolExecutor
        // 最大线程到底该如何定义
        // 1、CPU 密集型，几核，就是几，可以保持CPU的效率最高!
        // 2、IO 密集型 > 判断你程序中十分耗IO的线程，
        // 程序 15个大型任务 io十分占用资源! 设置  最大线程数大于io线程数
        // 获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy() // 【拒绝策略】：线程池满了，还有线程来，不处理，抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy() // 【拒绝策略】：线程池满了，还有线程来，哪儿来的去哪里
//                new ThreadPoolExecutor.DiscardOldestPolicy() // 【拒绝策略】：线程池满了，丢掉任务，不抛异常
                new ThreadPoolExecutor.DiscardPolicy() // 【拒绝策略】：线程池满了，尝试去和最早的线程竞争，不抛异常
        );

        try {
            // 最大承载数： Deque + max
            for (int i = 1; i <= 20; i++) {
                final int temp  = i;
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(String.valueOf(temp) + "-" + Thread.currentThread().getName() + " ok");
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
