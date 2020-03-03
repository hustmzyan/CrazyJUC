package com.mzyan._03_lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是锁的八个问题
 * 3. 增加了一个普通方法后，先打印 sendSms 还是 hello ？ 普通方法  hello - sendSms
 * 4. 两个对象，两个同步方法，先 sendSms，还是先 call？  sendSms -> call
 * Created by mzYan on 2020-03-02 17:46
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个不同的对象，有两个调用者
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

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

class Phone2 {

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

    // 这里没有锁！不是同步方法，不受锁的影响。
    public void hello(){
        System.out.println("hello");
    }

}
