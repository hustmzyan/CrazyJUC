package com.mzyan._17_lock;

/**
 * synchronized 实现可重入锁
 * Created by mzYan on 2020-03-03 23:23
 */
public class Demo01 {
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

class Phone {

    // 可重入锁
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName() + " sms");
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " call");
    }
}
