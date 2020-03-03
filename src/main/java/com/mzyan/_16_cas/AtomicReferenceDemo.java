package com.mzyan._16_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by mzYan on 2020-03-03 22:58
 */
public class AtomicReferenceDemo {

    //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
    // 正常在业务操作，这里面比较的都是一个个对象
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    // CAS compareAndSet: 比较并交换
    public static void main(String[] args) {
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获取版本号
            System.out.println("A1 => " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1,2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println("A2 => " + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A3 => " + atomicStampedReference.getStamp());
        }, "A").start();

        // 乐观锁原理相同
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("B1 => " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("B2 => " + atomicStampedReference.getStamp());
        }, "B").start();

    }
}
