package com.wt.test.spring.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: qiyu
 * @date: 2020/6/29 16:32
 * @description: VM Args：-Xmx2M -XX:MaxDirectMemorySize=1M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
