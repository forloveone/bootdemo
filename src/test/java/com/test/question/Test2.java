package com.test.question;

public class Test2 {
    String str = new String("good");
    char[] chars = {'a', 'b', 'c'};

    public static void main(String[] args) {
        Test2 test = new Test2();
        test.exchange(test.str, test.chars);
        System.out.println(test.str + "   " + new String(test.chars));
    }

    public void exchange(String str, char[] chars) {
        str = "test ok";
        chars[1] = 'g';
    }
}
