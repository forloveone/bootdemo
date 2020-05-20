package com.test;

import org.junit.Test;

import java.util.Scanner;

/**
 * java 小例子
 */
public class Fun {
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
    public void start() {
        //双for循环
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    @Test
    public void tuZhi() {
        System.out.println(Fun.tuZhi(24));
    }

    /**
     * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小 兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 规律:1
     * 1 2 3 5 8 13
     *
     * @param mon 月数
     * @return 对数
     */
    public static int tuZhi(int mon) {
        if (mon < 3) {
            return 1;
        } else if (mon >= 3) {
            int sum = tuZhi(mon - 1) + tuZhi(mon - 2);
            return sum;
        }
        return -1;
    }

    /**
     * 求100-201之间的素数
     */
    @Test
    public void shushu() {
        int x = 100;
        int y = 250;
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
    @Test
    public void flower() {
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
     * 利用条件运算符的嵌套来完成此题：学习成绩>=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
     * 1.程序分析：(a>b)?a:b这是条件运算符的基本例子。
     */
    @Test
    public void qianTao() {
        int mark = 90;
        String ma = (mark >= 90) ? "A" : ((mark >= 60 & mark <= 89) ? "B" : "C");
        System.out.println(ma);
    }

    public static void main(String[] args) {
        System.out.println("请选择你喜欢的运动");
        System.out.println("1.足球");
        System.out.println("2.篮球");
        System.out.println("3.羽毛球");
        System.out.println("4.乒乓球");
        Scanner xx = new Scanner(System.in);
        int choice = xx.nextInt();
        //死循环的应用
        while (true) {
            if (choice == 1) {
                System.out.println("你喜欢足球");
                break;
            } else if (choice == 2) {
                System.out.println("你喜欢篮球");
                break;
            } else if (choice == 3) {
                System.out.println("你喜欢羽毛球");
                break;
            } else {
                System.out.println("你输入的有误,请重新输入!");
                choice = xx.nextInt();
            }
        }
    }
}
