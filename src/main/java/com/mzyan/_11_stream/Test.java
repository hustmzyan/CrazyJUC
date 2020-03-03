package com.mzyan._11_stream;

import java.util.Arrays;
import java.util.List;

/**
 * 函数式接口 - lambda 表达式 - 链式编程 - 流式计算
 * 一个题目搞定
 *
 * 题目要求:一分钟内完成此题，只能用一行代码实现! * 现在有5个用户!筛选:
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户!
 *
 * Created by mzYan on 2020-03-03 17:25
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        List<User> lists = Arrays.asList(u1, u2, u3, u4, u5);

        lists.stream()
                .filter(u->{return u.getId()%2 == 0;})
                .filter(u->{return u.getAge() > 23;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((uu1, uu2)->{return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);
    }
}
