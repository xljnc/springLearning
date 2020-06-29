package com.wt.test.spring.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiyu
 * @date: 2020/6/29 16:01
 * @description: VM args: -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapStringOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
