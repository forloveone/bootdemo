package com.springboot.bussiness.controller;

import com.springboot.bussiness.pojo.People;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/validata")
public class Validata {

    /**
     * 还需要post man 整个对象做入参 TODO
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void valid(@Validated People people, BindingResult br) {
        List<ObjectError> allErrors = br.getAllErrors();
        String errors = allErrors.toString();
        System.out.println(errors);
    }
}
