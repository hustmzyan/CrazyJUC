package com.mzyan._17_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock 实现可重入锁
 * Created by mzYan on 2020-03-03 23:28
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sms();
        }, "A").start();

        new Thread(()->{
            phone.sms();
        }, "B").start();
    }
}

class Phone2 {

    Lock lock = new ReentrantLock();

    // 可重入锁
    public void sms(){
        lock.lock();
        // 锁必须配对，否则就会死在里面
        try{
            System.out.println(Thread.currentThread().getName() + " sms");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

