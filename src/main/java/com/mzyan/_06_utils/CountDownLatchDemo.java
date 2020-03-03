package com.mzyan._06_utils;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 减法计数器
 * Created by mzYan on 2020-03-02 21:51
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 总数是 6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await(); // 等待计数器归零

        System.out.println("close door");
    }
}
