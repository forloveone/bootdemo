package com.springboot.Exeception;

public class TestException extends RuntimeException {
    public TestException(){

    }
    public TestException(String msg){
        super(msg);
    }
}
