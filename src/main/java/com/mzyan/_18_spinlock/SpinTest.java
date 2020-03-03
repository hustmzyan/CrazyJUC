package com.mzyan._18_spinlock;

import java.util.concurrent.TimeUnit;

/**
 * Created by mzYan on 2020-03-03 23:43
 */
public class SpinTest {
    public static void main(String[] args) throws InterruptedException {

        // 底层使用的是自旋锁CAS
        SpinlockDemo lock = new SpinlockDemo();


        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "T2").start();
    }
}
