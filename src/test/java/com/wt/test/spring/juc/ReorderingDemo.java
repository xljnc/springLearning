package com.wt.test.spring.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: qiyu
 * @date: 2020/6/10 10:55
 * @description:
 */
public class ReorderingDemo {
    static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws Exception {

        while (true) {
            x = y = a = b = 0;
            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread two = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            two.start();
            one.join();
            two.join();
            if (x == 0 && y == 0)
                System.out.println(x + " " + y);
        }
    }

}
