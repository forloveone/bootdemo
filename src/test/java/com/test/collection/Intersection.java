package com.test.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
}
