package com.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池内存溢出
 * 1.7 以上的版本不会发生,因为常量池的实现不同了
 */
public class ConstantPoolOOM {
    /**
     * -XX:PermSize10M -XX:MaxPermSize10M
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
