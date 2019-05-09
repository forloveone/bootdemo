package com.springboot.cal;

import org.junit.Test;

/**
 * 小练习
 */
public class Intrester {

    @Test
    public void test() {
        int sum = Intrester.tuZhi(24);
        System.out.println(sum);
        // 2.程序都是调出来的
        Intrester.shushu(100, 250);
        Intrester.flower();
        Intrester.fenjie(60);
        Intrester.qianTao(59);
    }

    /**
     * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小 兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 规律:1
     * 1 2 3 5 8 13
     *
     * @param mon
     * 月数
     * @return 对数
     */
    static int sum = 0;

    public static int tuZhi(int mon) {
        if (mon < 3) {
            return 1;
        } else if (mon >= 3) {
            sum = tuZhi(mon - 1) + tuZhi(mon - 2);
            return sum;
        }
        return -1;
    }

    /**
     * 求100-201之间的素数
     *
     * @param x x大于2
     * @param y y大于x
     */
    public static void shushu(int x, int y) {
        for (; x < y; x++) {
            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    break;
                }
                if (i == x - 1) {
                    System.out.println(x + "  " + "是个素数");
                }
            }
        }
    }

    /**
     * 打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，
     * 其各位数字立方和等于该数本身。例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。
     */
    public static void flower() {
        for (int i = 100; i <= 999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100;
            if (ge * ge * ge + shi * shi * shi + bai * bai * bai == i) {
                System.out.println(i + "是一个水仙花数");
            }
        }
    }

    /**
     * 将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：
     * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
     * (2)如果n<>k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数你n,重复执行第一步。
     * (3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
     */
    static int k = 0;

    public static String fenjie(int x) {
        StringBuffer sb = new StringBuffer(x + "=");
        int[] arr = new int[50];
        for (int i = 2; i <= x; i++) {
            if (x % i == 0) {
                if (i == x) {
                    arr[k] = i;
                } else if (x != i && x % i == 0) {
                    arr[k] = i;
                    k++;
                    x = x / i;
                } else if (x / i != 0) {
                    continue;
                }
            }
        }
        int j = k;
        for (; j >= 0; j--) {
            if (j != 0 && arr[j] != 0) {
                sb.append(arr[j] + "*");
            } else if (arr[j] != 0) {
                sb.append(arr[j] + ".");
            }
        }
        System.out.println(sb.toString());
        return null;
    }

    /**
     * 利用条件运算符的嵌套来完成此题：学习成绩>=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
     * 1.程序分析：(a>b)?a:b这是条件运算符的基本例子。
     */
    public static void qianTao(int mark) {
        String ma = (mark >= 90) ? "A" : ((mark >= 60 & mark <= 89) ? "B" : "C");
        System.out.println(ma);
    }
}
