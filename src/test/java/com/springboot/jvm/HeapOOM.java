package com.springboot.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 */
public class HeapOOM {
    static class OOMObject{

    }

    /**
     * 堆内存溢出,使用参数
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError (转储快照到项目根目录下)
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
