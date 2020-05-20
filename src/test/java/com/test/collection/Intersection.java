package com.test.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 两个数组,第一个和第二个,看减少了什么,增加了什么
 * 输出增加的为6,8 减少的为1,4,5
 * <p>
 * 就是两个集合 A B
 * ∩∪ 补集
 */
public class Intersection {
    private List<Integer> a = new ArrayList<>();
    private List<Integer> b = new ArrayList<>();

    @Before
    public void init() {
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        b.add(2);
        b.add(3);
        b.add(6);
        b.add(8);
    }

    /**
     * A 和 B 的交集
     * [2, 3]
     */
    @Test
    public void 交集() {
        //a.retainAll(b);
        b.retainAll(a);
        System.out.println(b);
    }

    /**
     * A 和 B 的并集
     * [1, 2, 3, 4, 5, 6, 8]
     */
    @Test
    public void 并集() {
        a.addAll(b);
        Set<Integer> set = new LinkedHashSet<>(a);
        List<Integer> list = new ArrayList<>(set);
        System.out.println(list);
    }

    /**
     * 求A的差集
     * [1, 4, 5]
     */
    @Test
    public void 差集() {
        b.retainAll(a);
        a.removeAll(b);
        System.out.println(a);
    }

    /**
     * 要求拉链法求交集
     */
    @Test
    public void testZipperListIntersection() {
        String[] str = {"1", "3", "5", "7", "8", "9"};
        String[] str2 = {"2", "3", "4", "5", "5", "5"};
        List<String> list = Arrays.asList(str);
        List<String> list2 = Arrays.asList(str2);
        List<String> returnList = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        while (true) {
            count++;
            if (list.get(index1).equals(list2.get(index2))) {
                returnList.add(list.get(index1));
            } else {
                //指针较小的一个,加一,直到某个队列的队未,结束

            }
        }

//        System.out.println(returnList.toString());
//        System.out.println(count);
    }
}
