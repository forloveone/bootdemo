package com.springboot.json;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    /**
     * split test
     */
    @Test
    public void test() {
        String testS = "reque_id:123:dis_id:666";
        String[] temp = testS.split(":");
        System.out.println(temp[temp.length - 1]);

        //特殊的需要\转译
        String test2 = "123|32|33";
        String[] temp2 = test2.split("\\|");
        System.out.println(temp2.toString());
    }

    /**
     * UUID
     */
    @Test
    public void uuid() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        System.out.printf(uuid.toString().replace("-", ""));
    }

    //整个的运算结果为ACDB  B
    /**
     * https://www.cnblogs.com/xiaoxiaoyihan/p/4883770.html
     * Java对对象采用的不是引用调用，实际上，对象引用进行的是值传递
     * Java总是采用按值调用。方法得到的是所有参数值的一个拷贝，特别的，方法不能修改传递给它的任何参数变量的内容。
     */
    @Test
    public void test2() {
        StringBuffer a = new StringBuffer("A");
        a.append("CD");
        StringBuffer b = new StringBuffer("B");
        operate(a, b);
        System.out.println(a + "  " + b);

        int x = 10;
        testInt(x);
        System.out.println(x);
    }

    /**
     * 一个方法不能让对象参数引用一个新的对象
     * 一个方法可以改变一个对象参数的状态
     */
    private void operate(StringBuffer x, StringBuffer y) {
        x.append(y);
        y = x;
    }

    /**
     * 一个方法不能修改一个基本数据类型的参数(即数值型和布尔型)
     */
    private void testInt(int a) {
        a = 2 * a;
    }

    /**
     * indexOf
     * substring
     * contains
     * replace
     * toUpperCase
     * toLowerCase
     * split
     * matches
     */
    @Test
    public void testString() {
    }

    @Test
    public void regexp() {
        String s = "Please initialize the log4j system properly";
        String news = s.replaceAll("\\s", "%n");
        System.out.println(news);

        //查找这个string中有多少和给定的字符串匹配的项
        String str = ".jpg asd.jpgfasd < img < img";
        Pattern p = Pattern.compile("< img", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            count++;
        }
        System.out.println(count);
    }

    /**
     * 字符串中有特殊含义字符,需要转义
     */
    @Test
    public void test6() {
        String str = "1|2|3|4";
        String str2 = "1*2*3*4";
        String[] strs = str.split("\\|");
        String[] strs2 = str2.split("\\*");

        String s = "10.1.1.2";
        String[] temp = s.split("\\.");
        for (String t : temp) {
            System.out.println(t);
        }
    }

    /**
     * 生成随机字符串
     */
    @Test
    public void test7() {
        for (int i = 0; i < 10; i++) {
            String s = generateVerifyCode();
            System.out.println(s);
        }
        String test = RandomStringUtils.randomAlphabetic(10);
        System.out.println(test);
    }

    //生成随机定长字符串 生成规则配套,62位的数字+字母随机
    private String generateVerifyCode() {
        String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        StringBuilder sbBuilder = new StringBuilder();
        for (int i = 0; i < 62; i++) {
            char charAt = s.charAt(r.nextInt(62));
            sbBuilder.append(charAt);
        }
        return sbBuilder.toString();
    }

    @Test
    public void tuXingShuChu() {
        for (int x = 1; x <= 5; x++) {
            for (int y = 1; y < x; y++) {
                System.out.print(" ");
            }
            for (int z = x; z <= 5; z++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("----------------");
        for (int i = 1; i <= 5; i++) {
            for (int j = i; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("----------");

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("----------");
        /*
         * 54321 5432 543 54 5
         */
        for (int x = 1; x <= 5; x++) {
            for (int y = 5; y >= x; y--) {
                System.out.print(y);
            }
            System.out.println();
        }
        System.out.println("----------");
        /*
         * 1 22 333 4444 55555
         */
        for (int x = 1; x <= 5; x++) {
            for (int y = 1; y <= x; y++) {
                System.out.print(x);
            }
            System.out.println();
        }
    }

    /**
     *  public AbstractStringBuilder append(String str) {
     *         if (str == null)
     *             return appendNull();
     *         int len = str.length();
     *         ensureCapacityInternal(count + len); //
     *         str.getChars(0, len, value, count);
     *         count += len;
     *         return this;
     *     }
     *
     *     是默认16
     *     会根据 它会将自身容量增加到当前的2倍再加2
     *     所以如果能预估出字符串长度,可以减少底层的数组扩容
     *
     *     可以断点跟进去看看
     */
    @Test
    public void stringBuilderTest(){
        StringBuilder sb = new StringBuilder(20);
        sb.append("1234567890abcdef1234");//20
        sb.append("123");
    }
}
