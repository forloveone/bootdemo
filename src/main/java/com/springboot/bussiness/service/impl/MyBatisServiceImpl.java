package com.springboot.bussiness.service.impl;

import com.springboot.bussiness.dao.UserMapper;
import com.springboot.bussiness.pojo.User;
import com.springboot.bussiness.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
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

    @Override
    //被注解的方法发生异常会重试 value:指定发生的异常进行重试 maxAttemps:重试次数，默认3 backoff:重试补偿机制，默认没有 delay:指定延迟后重试
    //multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒
    @Retryable(value = {RemoteAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 1))
    public void retryTest() {
        System.out.println("retry");
        throw new RemoteAccessException("异常需要重试");
    }
    @Recover
    //当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
    public void recover(RemoteAccessException e) {
        System.out.println(e.getMessage());
    }
}
