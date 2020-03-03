package com.mzyan._03_lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是锁的八个问题
 * 1. 标准情况下，两个线程先打印“sendSms”还是“call”?  sendSms -> call
 * 2. sendSms 延迟4秒后，顺序又会是怎样?  call -> sendSms
 *
 * Created by mzYan on 2020-03-02 17:38
 */
public class Test1 {
    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSms();
        }).start();

        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        }).start();

    }
}

class Phone {

    // synchronized 锁的对象是方法的调用者!
    // 两个方法用的是同一个锁，谁先拿到，谁执行
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public synchronized void call() {
        System.out.println("call");
    }

}
