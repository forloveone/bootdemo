package com.springboot.bussiness.service.impl;

import com.springboot.bussiness.dao.UserMapper;
import com.springboot.bussiness.pojo.User;
import com.springboot.bussiness.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatisServiceImpl implements MyBatisService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
