package com.mzyan._01_demo01;

/**
 * synchronized 实现卖票
 * Created by mzYan on 2020-03-02 16:07
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：对个线程操作同一个资源类，把资源丢入线程池
        Ticket ticket = new Ticket();

        // @FunctionalInterface 函数式接口，jdk 1.8  lambda表达式 (参数)->{ 代码 }
        // 解耦操作
        new Thread(()->{
            for(int i = 0; i < 15; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(()->{
            for(int i = 0; i < 15; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(()->{
            for(int i = 0; i < 15; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

// 资源类 OOP
class Ticket {
    // 属性、方法
    private int number = 30;

    // 买票的方式
    // synchronized : 本质是队列，锁
    public synchronized void sale() {
        if(number > 0){
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "票，剩余: " + number);
        }
    }
}
