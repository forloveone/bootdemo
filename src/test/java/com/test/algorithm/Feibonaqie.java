package com.test.algorithm;
//斐波那契数列
public class Feibonaqie {
    public static void main(String[] args) {
        int fb = getFB(9);
        System.out.println(fb);
    }

    public static int getFB(int i) {
        if (i <= 1) {
            return i;
        } else {
            int x = getFB(i - 1);
            int y = getFB(i - 2);
            return x + y;
        }
    }
}
