package com.test.base.collection;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTest {

    @Test
    public void test() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int anInt = random.nextInt(100);
            if (anInt > 90) {
                System.out.println("A");
            } else {
                System.out.println("B");
            }
        }

    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            double a = Math.random();
            System.out.println(a);
        }
    }

    /**
     * 8.88 到 18.88 之间随机
     */
    @Test
    public void test3() {
        for (int i = 0; i < 15; i++) {
            double a = RandomUtils.nextDouble(new Double("8.88"), new Double("18.88"));
            System.out.println(new BigDecimal(a).setScale(2, RoundingMode.HALF_DOWN));
        }
    }

    /**
     * 加权随机
     * A B C 出现的比例为  100 700 200
     */
    @Test
    public void test4() {
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        List<String> listC = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            int anInt = RandomUtils.nextInt(1, 1001);
//            System.out.println(anInt);
            if (anInt <= 100) {
                listA.add("A");
            } else if (anInt <= 800) {
//                System.out.println("B");
                listB.add("B");
            } else if (anInt <= 1000) {
//                System.out.println("C");
                listC.add("C");
            }
        }

        System.out.println(listA.size());
        System.out.println(listB.size());
        System.out.println(listC.size());
        System.out.println(listC.size() + listA.size() + listB.size());
    }

    /**
     * 权会变动的加权随机
     * 1000
     */
    @Test
    public void test5() {
        int anInt = new Random().nextInt(100 - 20);
        System.out.println(20 + anInt);
    }
}
