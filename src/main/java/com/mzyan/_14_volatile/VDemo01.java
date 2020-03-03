package com.mzyan._14_volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile 可见性证明
 * Created by mzYan on 2020-03-03 20:16
 */
public class VDemo01 {
    private volatile static int num = 0; // 增加可见性

    public static void main(String[] args) {

        new Thread(()->{  // 线程 1 对主内存的变化是不知道的
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;

        System.out.println(num);

    }
}
