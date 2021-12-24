package com.dujinyue.bussiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class SecurityController {

    @RequestMapping("/in")
    public void test() {
        System.out.println("登录");
    }
}
