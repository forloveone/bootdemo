package com.springboot.bussiness.controller;

import com.springboot.bussiness.pojo.User;
import com.springboot.bussiness.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/db")
public class MyBatisController {

    @Autowired
    private MyBatisService myBatisService;

    @RequestMapping("/insert")
    public void insert() throws Exception {
        User user = new User();
        user.setName("百万");
        user.setPassword("test");
        user.setAddTime(new Date());
        myBatisService.insert(user);
    }

    @RequestMapping("/callProduce")
    public void call(){
        myBatisService.callProduce();
    }

    @RequestMapping("/batchInsert")
    public void batchInsert(){
        myBatisService.batchInsert();
    }
}
