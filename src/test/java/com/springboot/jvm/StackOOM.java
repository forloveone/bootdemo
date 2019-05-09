package com.springboot.jvm;

/**
 * 栈内存溢出
 */
public class StackOOM {
    private void dontStop(){
        while (true) {

        }
    }

    public void stackLeakByThread(){
        while (true) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            t.start();
        }
    }

    /**
     * -Xss2M
     * java线程是映射到操作系统的内核线程上的，会导致操作系统假死
     */
    public static void main(String[] args) {
        StackOOM out = new StackOOM();
        out.stackLeakByThread();
    }
}
