package com.mzyan._03_lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是锁的八个问题
 * 5. 增加两个静态的同步方法，只有一个对象，先打印 sendSms 还是 call？ sendSms -> call
 * 6. 两个对象，增加两个静态的同步方法，只有一个对象，先打印 sendSms 还是 call？ sendSms -> call
 * Created by mzYan on 2020-03-02 17:52
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(()->{
            phone1.sendSms();
        }).start();

        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        }).start();
    }
}

class Phone3 {

    // synchronized 锁的对象是方法的调用者!
    // static 静态方法
    // 类一加载就有了，锁的是class
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public static synchronized void call() {
        System.out.println("call");
    }
}