package com.mzyan._04_unsafe;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 * Created by mzYan on 2020-03-02 21:06
 */
public class MapTest {
    public static void main(String[] args) {
        /**
         * map 是这样用的吗？ 不是，工作中不用 HashMap
         * 默认等价于什么？ new HashMap<>(16, 0.75);
         *
         * 解决方案：
         * 1. Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
         * 2. Map<String, String> map = new ConcurrentHashMap<>();
         */
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i= 1; i <= 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
