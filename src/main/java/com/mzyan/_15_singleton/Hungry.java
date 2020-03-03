package com.mzyan._15_singleton;

/**
 * 饿汉式单例
 * Created by mzYan on 2020-03-03 21:45
 */
public class Hungry {

    // 可能会浪费空间
    private byte[] bytes1 = new byte[1024*1024];
    private byte[] bytes2 = new byte[1024*1024];
    private byte[] bytes3 = new byte[1024*1024];
    private byte[] bytes4 = new byte[1024*1024];

    private Hungry(){}

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
