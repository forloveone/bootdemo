package com.springboot.bussiness.service;

import com.springboot.bussiness.pojo.User;

public interface MyBatisService {
    public void insert(User user) throws Exception;

    /**
     * 调用存储过程
     */
    void callProduce();

    void retryTest();
}
