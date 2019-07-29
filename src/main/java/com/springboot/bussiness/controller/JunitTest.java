package com.springboot.bussiness.controller;

import com.springboot.bussiness.pojo.TestPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 调用远程方法,数据库操作(rollback),静态方法
 */
@Controller
@RequestMapping("/test")
public class JunitTest {

    @RequestMapping("/1")
    public void test() {
        System.out.println("123");
    }

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public void hasParam(String text) {
        System.out.println(text);
    }

    @RequestMapping(value = "/3", method = RequestMethod.POST)
    public void hasPojo(HttpServletRequest request, @RequestBody TestPojo pojo) {
        System.out.println(pojo);
    }

    @RequestMapping(value = "/4", method = RequestMethod.POST)
    @ResponseBody
    public String hasReturn(String param1, String param2) {
        System.out.println(param1 + "    " + param2);
        return param1 + param2;
    }

    @RequestMapping(value = "/5", method = RequestMethod.POST)
    @ResponseBody
    public String mock() {
        return "mock";
    }
}
