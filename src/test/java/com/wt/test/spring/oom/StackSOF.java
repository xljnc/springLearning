package com.wt.test.spring.oom;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: qiyu
 * @date: 2020/6/29 15:46
 * @description: VM args:-Xss2m
 */
@Slf4j
public class StackSOF {

    private static int stackLength = 1;

    public static void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        try {
            stackLeak();
        } catch (Throwable e) {
            log.error(String.valueOf(stackLength),e);
        }
    }
}
