package com.mzyan._18_spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * Created by mzYan on 2020-03-03 23:34
 */
public class SpinlockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ==> myLock");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ==> myUnLock");
        atomicReference.compareAndSet(thread, null);
    }
}