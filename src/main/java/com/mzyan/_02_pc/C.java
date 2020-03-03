package com.mzyan._02_pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A 执行完 调用 B，B执行完调用 C，C 执行完调用 A
 * JUC condition 精准通知和唤醒线程，实现生产者消费者
 * Created by mzYan on 2020-03-02 17:22
 */
public class C {

    public static void main(String[] args) {

        Data3 data = new Data3();

        new Thread(()->{
            for(int i = 0; i < 10; i++) data.printA();
        }, "A").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++) data.printB();
        }, "B").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++) data.printC();
        }, "C").start();
    }
}



class Data3 {

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int number = 1;

    public void printA(){
        lock.lock();
        try{
            // 业务，判断 -> 执行 -> 通知
            while (number != 1){
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAA");
            number = 2;
            condition2.signal();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try{
            // 业务，判断 -> 执行 -> 通知
            while (number != 2){
                // 等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBB");
            number = 3;
            condition3.signal();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            // 业务，判断 -> 执行 -> 通知
            while (number != 3){
                // 等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCC");
            number = 1;
            condition1.signal();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}