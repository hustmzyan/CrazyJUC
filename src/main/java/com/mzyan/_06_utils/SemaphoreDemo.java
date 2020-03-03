package com.mzyan._06_utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore 信号量
 * 功能：限流
 * 示例：停车位
 * Created by mzYan on 2020-03-02 22:06
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 进入停车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " 离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
