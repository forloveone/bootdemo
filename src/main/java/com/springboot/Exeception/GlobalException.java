package com.springboot.Exeception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
        //TODO 为什么没有打印轨迹
        log.error("发生错误"+ex);
    }
}
