package com.springboot.bussiness.service.impl;

import com.springboot.bussiness.dao.UserMapper;
import com.springboot.bussiness.pojo.User;
import com.springboot.bussiness.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyBatisServiceImpl implements MyBatisService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void insert(User user) throws Exception {
        userMapper.insert(user);
        throw new RuntimeException("test");
    }

    @Override
    public void callProduce() {
        /*
        CREATE DEFINER=`root`@`localhost` PROCEDURE `procedureDemo`()
BEGIN
		select * from usertest;
END
         */
        List<User> users = userMapper.callTest();
    }
}
