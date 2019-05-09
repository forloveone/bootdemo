package com.springboot.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapIterator {
    private Map<String, String> map;

    @Before
    public void init() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Student");
        map.put("2", "Student2");
        map.put("3", "Student3");
        map.put("4", "Student4");
        this.map = map;
    }

    /**
     * map 遍历的几种方式
     */
    @Test
    public void test() {
        // 1
        for (String key : map.keySet()) {
            System.out.println(key + "          " + map.get(key));
        }
        System.out.println("\n");

        // 2 这种方式效率最高
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
        System.out.println("\n");

        // 3 和 2 是等效的
        for (Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
        System.out.println("\n");

        // 4
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
        System.out.println("\n");

        //jdk8
        map.forEach((k, v) -> System.out.println("key : " + k + "; value : " + v));

        map.forEach((k, v) -> {
            System.out.println("Item : " + k + " Count : " + v);
            if ("4".equals(k)) {
                System.out.println(v);
            }
        });
    }
}
