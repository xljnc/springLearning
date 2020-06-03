package com.wt.test.spring.dp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auther: 埼玉
 * @date: 2019/3/20 16:11
 * @description:
 */
public class FirstInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("FirstInvocationHandler invoke");
        return null;
    }
}
