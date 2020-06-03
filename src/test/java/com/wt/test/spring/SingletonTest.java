package com.wt.test.spring;

import java.util.concurrent.*;

/**
 * @author: qiyu
 * @date: 2020/6/1 13:45
 * @description:
 */
public class SingletonTest {

    private static SingletonTest singletonTest = null;

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static SingletonTest getInstance() {
        if (singletonTest == null) {
            synchronized (SingletonTest.class) {
                if (singletonTest == null)
                    singletonTest = new SingletonTest();
            }
        }
        return singletonTest;
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                System.out.println(getInstance());
                cdl.countDown();
            });
        }
        cdl.await();
    }
}


