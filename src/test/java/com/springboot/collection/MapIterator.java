package com.springboot.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MapIterator {
    private Map<String, String> map;

    @Before
    public void init() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Student");
        map.put("2", "Student2");
        map.put("3", "Student3");
        map.put("4", "Student4");
        map.put(null,"test");
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

    /**
     * TreeMap默认是根据Key来比较来排序的
     * TreeMap的构造方法允许使用指定的比较器来比较
     */
    @Test
    public void treeMap(){
        // 创建TreeMap ,可以自定义比较器,在初始化的时候构造进去
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(2,4);
        treeMap.put(1,5);
        treeMap.put(4,2);
        treeMap.put(3,3);
        treeMap.put(7,1);
        treeMap.put(5,1);

        // 迭代TreeMap的结果
        Iterator<Map.Entry<Integer,Integer>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer,Integer> entry = iterator.next();
            System.out.println("key : "+entry.getKey()+" value : " + entry.getValue());
        }
        System.out.println("after sorted, the size : " +treeMap.size());
    }
}
