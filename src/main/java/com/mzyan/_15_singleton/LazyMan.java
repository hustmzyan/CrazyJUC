package com.mzyan._15_singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例
 * 道高一尺、魔高一丈
 * Created by mzYan on 2020-03-03 21:51
 */
public class LazyMan {

    private static boolean qinjiang = false;

    private LazyMan(){
        synchronized (LazyMan.class) {
            if (qinjiang == false) {
                qinjiang = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
    }

    private volatile static LazyMan lazyMan;


    // 双重检测锁模式的懒汉式单例  DCL 模式
    public static LazyMan getInstance(){
        if (lazyMan == null) {
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan();  // 不是原子操作
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化对象
                     * 3. 把这个对象指向这个空间
                     *
                     * 指令重排 123 ->  132（此时lazyman还没有完成构造）
                     */
                }
            }
        }
        return lazyMan;
    }

    // 反射
    public static void main(String[] args) throws Exception {
        Field qinjiang = LazyMan.class.getDeclaredField("qinjiang");
        qinjiang.setAccessible(true);
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance = declaredConstructor.newInstance();
        qinjiang.set(instance, false);
        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance2);
    }
}
