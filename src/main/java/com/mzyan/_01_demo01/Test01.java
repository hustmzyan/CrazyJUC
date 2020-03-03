package com.mzyan._01_demo01;

/**
 * Created by mzYan on 2020-03-02 15:45
 */
public class Test01 {

    public static void main(String[] args) {
        // 获取 CPU 核数
        // CPU 密集型  IO 密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
