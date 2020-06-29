package com.wt.test.spring.juc;

/**
 * @author: qiyu
 * @date: 2020/6/10 14:25
 * @description:
 */
public class ThreadInterruptTest {

    public static void main(String[] args)  throws InterruptedException{
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread interrupted");
            }
        });
        t.start();
        Thread.sleep(3000);
        t.interrupt();
    }
}
