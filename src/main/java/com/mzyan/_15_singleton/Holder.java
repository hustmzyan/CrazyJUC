package com.mzyan._15_singleton;

/**
 * 静态内部类实现单例模式
 * Created by mzYan on 2020-03-03 22:00
 */
public class Holder {
    private Holder() { }

    public static Holder getInstace(){
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}
