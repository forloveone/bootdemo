package com.springboot.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class LogBackTest {

    private static final Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    @Test
    public void logback(){
        logger.info("this is info ");
        logger.debug("this is debug");
        logger.error("this is error");
    }

    @Test
    public void lombok(){
        log.info("this is lombok info");
        log.debug("this is lombok debug");
        log.error("this is lombok error");
    }

    /**
     *  TODO log 中占位符的使用
     */
    @Test
    public void test(){
        log.info("hello,{}","women");
    }
}
