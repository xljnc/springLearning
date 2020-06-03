package com.wt.test.spring.dp;

import java.lang.reflect.Proxy;

/**
 * @auther: 埼玉
 * @date: 2019/3/20 16:15
 * @description:
 */
public class CarProxyTest {
    public static void main(String[] args) {
        Class<?>[] interfaces = {Car.class};
        Car carProxy = (Car)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),interfaces,new FirstInvocationHandler());
        carProxy.run();
        carProxy.beep();
    }
}
