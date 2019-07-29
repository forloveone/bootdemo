package com.test.jvm;

/**
 * 栈过深
 */
public class StackSOF {
    private int length = 1;

    public void stackLeak(){
        length++;
        stackLeak();
    }

    /**
     *
     * -Xss128k
     * @param args
     */
    public static void main(String[] args) {
        StackSOF sof = new StackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println(sof.length);
            e.printStackTrace();
        }
    }
}
