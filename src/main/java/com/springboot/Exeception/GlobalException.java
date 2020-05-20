package com.springboot.Exeception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.management.ManagementFactory;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalException {
//    想跳转统一错误页面
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handlerException(Exception ex){
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("ex",ex.getMessage());
//        return mv;
//    }

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception ex){
        //为什么没有打印轨迹  -XX:-OmitStackTraceInFastThrow
        ex.printStackTrace();
        List<String> inputArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("虚拟机jvm配置参数"+inputArgs);
        log.error("发生错误",ex);
        log.info("发生错误{},{}","12","23");
    }
}
