package com.mzyan._02_pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC 实现生产者消费者
 * Created by mzYan on 2020-03-02 17:12
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            for(int i = 0; i < 10; i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}


class Data2 { // 数字 资源类

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock(); // 加锁
        try{
            // 业务代码
            while(number != 0){
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); // 解锁
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock(); // 加锁
        try{
            // 业务代码
            while(number == 0){
                // 等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); // 解锁
        }
    }
}