package com.springboot.cal;

import org.junit.Test;

/**
 * java运算相关
 */
public class Calculate {
    /**
     * 变量互换.
     */
    @Test
    public void convert() {
        int a = 3;
        int b = 5;
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.println(a + "        " + b);
    }

    /**
     * 用while循环分别计算100以内的奇数的和、偶数的和
     */
    @Test
    public void caculate() {
        int sumOdd = 0;// 奇数的和
        int sumEven = 0;// 偶数的和
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                sumEven += i;
            } else {
                sumOdd += i;
            }
        }
        System.out.println("100之内偶数的和：" + sumEven);
        System.out.println("100之内奇数的和：" + sumOdd);
    }

    /**
     * 用循环结构输出1000之内所有被5整除 10 的数，并且每行最多输出3个
     */
    @Test
    public void test2() {
        int i = 0;
        int j = 0;
        while (i < 1000) {
            if (i % 5 == 0) {
                System.out.print(i + "  ");
                j++;
                if (j % 3 == 0) {
                    System.out.println("\n");
                    j = 0;
                }
            }
            i++;
        }
    }

    /**
     * 输出九九乘法表
     */
    @Test
    public void multiplicationTable() {
        for (int i = 1; i < 10; i++) {// i是一个乘数
            for (int j = 1; j <= i; j++) {// j是另外一个乘数
                System.out.print(j + "*" + i + "=" + (i * j < 10 ? (" " + i * j) : i * j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 编程求：1+（1+2）+（1+2+3）+. . .+（1+2+3+. . .+100）
     */
    @Test
    public void addAdd() {
        int sum = 0;// 总和
        for (int i = 1; i <= 100; i++) {
            int tempSum = 0;// 临时和
            for (int j = 1; j <= i; j++) {
                tempSum += j;
            }
            sum += tempSum;
        }
        System.out.println(sum);
    }

    /**
     * 编程求：1！+2！+3！+4！+. . .+15！
     */
    @Test
    public void addFactorial() {
        long result = 0;
        for (int i = 1; i <= 15; i++) {
            int temp = 1;
            for (int j = 1; j <= i; j++) {
                temp *= j;
            }
            result += temp;
        }
        System.out.println(result);
    }

    /**
     * 输出星星
     */
    @Test
    public void test() {
        //双for循环
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
