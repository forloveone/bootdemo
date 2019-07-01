package com.springboot.data.json;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};

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


        change(str, ch);
        System.out.println(str);
        System.out.println(ch);
    }

    /**
     * 一个方法不能让对象参数引用一个新的对象
     * 一个方法可以改变一个对象参数的状态
     */
    private void operate(StringBuffer x, StringBuffer y) {
        x.append(y);
        y = x;
    }

    //
    private void change(String str2, char[] ch2) {
        str = "test ok";
        ch[0] = 'g';
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
     * public AbstractStringBuilder append(String str) {
     * if (str == null)
     * return appendNull();
     * int len = str.length();
     * ensureCapacityInternal(count + len); //
     * str.getChars(0, len, value, count);
     * count += len;
     * return this;
     * }
     * <p>
     * 是默认16
     * 会根据 它会将自身容量增加到当前的2倍再加2
     * 所以如果能预估出字符串长度,可以减少底层的数组扩容
     * <p>
     * 可以断点跟进去看看
     */
    @Test
    public void stringBuilderTest() {
        StringBuilder sb = new StringBuilder(20);
        sb.append("1234567890abcdef1234");//20
        sb.append("123");
    }

    @Test
    public void hiddenString() {
        String name = "王某mount";
        String test = "  ";
        test = null;
        System.out.println(StringUtils.isNotEmpty(test));
        //推荐使用isNotBlank 可以排除"   "这种情况
        System.out.println(StringUtils.isNotBlank(test));

        System.out.println(StringUtils.rightPad(StringUtils.left(name, 1), StringUtils.length(name), "*"));

        String s = StringUtils.rightPad("12", 36, "1");
        System.out.println(s);
    }

    /**
     * 编码转换
     * @throws UnsupportedEncodingException
     */
    @Test
    public void utf_8() throws UnsupportedEncodingException {
        String a = new String("到高处看一看吧---杜金跃".getBytes("utf-8"), "iso-8859-1");
        System.out.println(a);
        String b = new String(a.getBytes("iso-8859-1"), "utf-8");
        System.out.println(b);
        String c = "å°é«å¤çä¸çå§---æéè·";
        String f = new String(c.getBytes("iso-8859-1"), "utf-8");
        System.out.println(f);
    }

    /**
     * IP 其实是一个整数类型,所以mysql存储的时候用整形.
     */
    @Test
    public void ip(){
        long ipConvertNum = getIpConvertNum("255.255.255.2");
        System.out.println(ipConvertNum);
        String numConvertIp = getNumConvertIp(ipConvertNum);
        System.out.println(numConvertIp);
    }

    /**
     * 将数字转成ip地址
     *
     */
    public static String getNumConvertIp(long ipLong) {
        long mask[] = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
        long num = 0;
        StringBuffer ipInfo = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> (i * 8);
            if (i > 0)
                ipInfo.insert(0, ".");
            ipInfo.insert(0, Long.toString(num, 10));
        }
        return ipInfo.toString();
    }

    /**
     * 将ip 地址转换成数字
     *
     * @param ipAddress
     *            传入的ip地址
     * @return 转换成数字类型的ip地址
     */
    public static long getIpConvertNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);

        long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
        return ipNum;
    }
}
