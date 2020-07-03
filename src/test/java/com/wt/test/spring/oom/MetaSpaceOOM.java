package com.wt.test.spring.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiyu
 * @date: 2020/6/29 16:12
 * @description: VM args: -XX:MetaspaceSize=2m -XX:+HeapDumpOnOutOfMemoryError
 */
public class MetaSpaceOOM {

    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Object.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(
                    new MethodInterceptor() {
                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                            return proxy.invokeSuper(obj, args);
                        }
                    }
            );
            objects.add(enhancer.create());
        }
    }
}