package com.mzyan._16_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mzYan on 2020-03-03 22:27
 */
public class CASDemo {
    // CAS compareAndSet: 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 期望 与 更新，如果期望达到了，就更新


        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2021, 2020));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2020, 6666));
        System.out.println(atomicInteger.get());
    }
}
