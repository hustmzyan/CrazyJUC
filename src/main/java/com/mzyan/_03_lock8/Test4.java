package com.mzyan._03_lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是锁的八个问题
 * 7. 一个普通同步方法，一个静态的同步方法，只有一个对象，先打印 sendSms 还是 call？ call -> sendSms
 * 8. 一个普通同步方法，一个静态的同步方法，两个个对象，先打印 sendSms 还是 call？ call -> sendSms
 * Created by mzYan on 2020-03-02 18:03
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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

class Phone4 {

    // 静态同步方法，锁的是class类的模板
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    // 普通同步方法，所得是类的调用者
    public synchronized void call() {
        System.out.println("call");
    }
}
