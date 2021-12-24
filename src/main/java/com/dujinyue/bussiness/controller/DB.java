package com.dujinyue.bussiness.controller;

import com.dujinyue.bussiness.pojo.User;
import com.dujinyue.bussiness.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/db")
public class DB {

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
    public void call() {
        myBatisService.callProduce();
    }

    @RequestMapping("/batchInsert")
    public void batchInsert() {
        myBatisService.batchInsert();
    }

    //对于非幂等的请求（比如新增，更新操作），千万不要使用重试
    @RequestMapping("retry")
    public void retry() {
        myBatisService.retryTest();
    }
}
