package com.wt.test.spring.oom;

/**
 * @author: qiyu
 * @date: 2020/6/29 16:05
 * @description:
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        new String("计算机软件").intern();
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
