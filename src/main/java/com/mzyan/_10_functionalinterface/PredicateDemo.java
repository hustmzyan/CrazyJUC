package com.mzyan._10_functionalinterface;

import java.util.function.Predicate;

/**
 * 四大函数式接口!!!
 * 2. 断定型接口：有一个输入参数，返回值只能是布尔值!!!
 * Created by mzYan on 2020-03-03 17:05
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                return o.isEmpty();
            }
        };

        Predicate<String> predicate1 = str -> {return str.isEmpty();};

        System.out.println(predicate1.test(""));
    }
}
