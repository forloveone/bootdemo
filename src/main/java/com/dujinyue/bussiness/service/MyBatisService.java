package com.dujinyue.bussiness.service;

import com.dujinyue.bussiness.pojo.User;

public interface MyBatisService {
    public void insert(User user) throws Exception;

    /**
     * 调用存储过程
     */
    void callProduce();

    void retryTest();

    void batchInsert();
}
