package com.mzyan._14_volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不保证原子性
 * Created by mzYan on 2020-03-03 21:14
 */
public class VDemo02 {

    // volatile 不保证原子性
    private volatile static AtomicInteger num = new AtomicInteger(0);

    public static void add(){
        num.getAndIncrement(); // AtomicInteger +1 方法   原理是底层的CAS方法
    }

    public static void main(String[] args) {

        // 理论上，结果应该为 20000
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for(int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2){  // main gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
