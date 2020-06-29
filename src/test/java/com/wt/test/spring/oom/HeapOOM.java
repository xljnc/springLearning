package com.wt.test.spring.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiyu
 * @date: 2020/6/29 15:19
 * @description: VM args: -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        while (true){
            objectList.add(new Object());
        }
    }
}
