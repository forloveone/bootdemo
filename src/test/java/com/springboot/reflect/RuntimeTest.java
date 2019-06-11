package com.springboot.reflect;

import org.junit.Test;

import java.io.IOException;

public class RuntimeTest {
    /**
     * java 调用 操作系统服务
     */
    @Test
    public void test() throws IOException {
//        String [] cmd={"cmd","/C","copy exe1 exe2"};
        String [] cmd={"cmd","/C",""};
        Process proc =Runtime.getRuntime().exec("javac");
        int exitVal = proc.exitValue ();
        System.out.println ("Process exitValue: " + exitVal);
    }
}
