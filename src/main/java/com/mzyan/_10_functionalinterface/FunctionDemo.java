package com.mzyan._10_functionalinterface;

import java.util.function.Function;

/**
 * 四大函数式接口!!!
 * 1. Function函数式接口: 只有一个输入参数，有一个输出
 * 只要是 函数式接口 可以用lambda表达式简化
 * Created by mzYan on 2020-03-03 17:00
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // 工具类：输出输入的值
        Function function = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };

        Function function1 = str -> {return str;};
        System.out.println(function1.apply("abc"));
    }
}
