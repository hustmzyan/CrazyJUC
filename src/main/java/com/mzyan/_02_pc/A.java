package com.mzyan._02_pc;

/**
 * 线程之间的通信问题：生产者和消费者问题！  等待唤醒 通知唤醒  synchronized实现
 * 线程交替执行  A  B
 * A num+1
 * B num-1
 * Created by mzYan on 2020-03-02 16:52
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

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

class Data { // 数字 资源类

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while(number != 0){
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，我+1完毕
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(number == 0){
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，我-1完毕
        this.notifyAll();
    }
}