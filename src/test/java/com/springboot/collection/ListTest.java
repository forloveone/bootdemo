package com.springboot.collection;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.unit.DataUnit;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListTest {
    private List<String> list = new ArrayList<>();

    @Before
    public void before() {
        list.add("women");
        list.add("zhangsan");
        list.add("women");
        list.add("lishi");
        list.add("wangwu ");
    }

    /**
     * 使用HashSet不能保证原有的list中的顺序
     */
    @Test
    public void test() {
        Set<String> set = new HashSet<>(list);
        List<String> list2 = new ArrayList<>(set);
        System.out.println(list2);
    }

    /**
     * 使用linkedHashSet可以保证原来的list中的顺序
     */
    @Test
    public void test2() {
        Set<String> set = new LinkedHashSet<>(list);
        List<String> list2 = new ArrayList<>(set);
        System.out.println(list2);
    }

    /**
     * 这个也可以去重
     * 频率分布
     */
    @Test
    public void test5() {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (Collections.frequency(result, s) < 1) {
                result.add(s);
            }
        }
        System.out.println(result);
    }

    /**
     * String[]数组去重,使用
     */
    @Test
    public void test3() {
        String[] str = {"16", "18", "20", "15", "20", "16", "17"};
        List<String> list = Arrays.asList(str);
        HashSet<String> hashSet = new HashSet<>(list);
        String[] str2 = hashSet.toArray(new String[hashSet.size() - 1]);
        for (String strs : str2) {
            System.out.println(strs);
        }
    }

    //判断数组中是否包含给定字符串
    public boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * Java8 stream 特性完成两个list int 相加
     */
    @Test
    public void test4() {
        List l1 = new ArrayList();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        List l2 = new ArrayList();
        l2.add(11);
        l2.add(12);
        l2.add(13);
        l2.add(14);

        List<Integer> result = IntStream.range(0, l1.size())
                .map(i -> (Integer) l1.get(i) + (Integer) l2.get(i))
                .boxed()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 迭代中remove元素
     * 循环删除list中特定一个元素的，可以使用三种方式中的任意一种，但在使用中要注意上面分析的各个问题
     * 循环删除list中多个元素的，应该使用迭代器iterator方式。
     */
    @Test
    public void removeObject() {
        //这种会导致 list的length改变
        for (int i = 0, length = list.size(); i < length; i++) {
            if (i == 1) {
                list.remove(i);
            }
        }
        System.out.println();

        // 这种会报错 ConcurrentModificationException
        for (String s : list) {
            if ("women".equals(s)) {
                list.remove(s);
                break; //如果只删除一个元素立刻 退出循环则不会报错
            }
        }
        System.out.println("123");

        // 最优解
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String temp = it.next();
            if ("women".equals(temp)) {
                it.remove();
            }
        }
    }
}
