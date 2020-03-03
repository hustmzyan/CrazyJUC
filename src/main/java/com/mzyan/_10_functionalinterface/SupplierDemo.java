package com.mzyan._10_functionalinterface;

import java.util.function.Supplier;

/**
 * 四大函数式接口!!!
 * 4. 供给型接口：没有参数，只有返回值
 * Created by mzYan on 2020-03-03 17:13
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("get()");
                return 1024;
            }
        };

        Supplier supplier1 = () -> {return 1024;};

        System.out.println(supplier1.get());
    }
}
