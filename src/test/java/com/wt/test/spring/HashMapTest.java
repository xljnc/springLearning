package com.wt.test.spring;

import java.util.HashMap;

/**
 * @author: qiyu
 * @date: 2020/6/4 17:50
 * @description:
 */
public class HashMapTest {
    public static void main(String[] args) {
       final HashMap<Integer, Integer> map = new HashMap<>(2, 0.75f);
        map.put(5, 55);
        new Thread("Thread1") {
            public void run() {
                map.put(7, 77);
                System.out.println(map);
            }
        }.start();
        new Thread("Thread2") {
            public void run() {
                map.put(3, 33);
                System.out.println(map);
            }
        }.start();

    }
}
