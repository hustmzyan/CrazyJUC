package com.mzyan._10_functionalinterface;

import java.util.function.Consumer;

/**
 * 四大函数式接口!!!
 * 3. 消费型接口：只有输入，没有返回
 * Created by mzYan on 2020-03-03 17:10
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer<String> consumer1 = str -> {
            System.out.println(str);
        };

        consumer1.accept("abc");
    }
}
