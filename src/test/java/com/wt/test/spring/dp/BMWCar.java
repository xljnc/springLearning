package com.wt.test.spring.dp;

/**
 * @auther: 埼玉
 * @date: 2019/3/20 16:13
 * @description:
 */
public class BMWCar implements Car{
    @Override
    public void run() {
        System.out.println("run slow.");
    }

    @Override
    public void beep() {
        System.out.println("beep loudly.");
    }
}
