package com.dujinyue.bussiness.service.impl;

import com.dujinyue.bussiness.dao.UserMapper;
import com.dujinyue.bussiness.pojo.User;
import com.dujinyue.bussiness.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MyBatisServiceImpl implements MyBatisService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    //Transactional只能保证同时成功同时失败,并不能保证 并发的安全性.
    @Override
    public void insert(User user) throws Exception {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(10000);
        userMapper.insert(user);
//        throw new RuntimeException("test");
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

    /**
     * 这种批量插入比for循环中单条插入,每百条提交一次性能好的多,尤其在数据量很大的时候,
     * mysql默认接受sql的大小是1048576(1M)，若数据量(sql太长导致)超过1M会报如下异常：Packet for query is too large
     * （可通过调整MySQL安装目录下的my.ini文件中[mysqld]段的＂max_allowed_packet = 1M＂）
     *
     * 也支持回滚
     */
    @Override
    @Transactional
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        User u1 = new User("batch1","batchPassword1",new Date());
        User u2 = new User("batch2","batchPassword2",new Date());
        list.add(u1);
        list.add(u2);
        boolean flag = userMapper.batchInsert(list);
        throw new RuntimeException("error Batch insert");
    }

    @Recover
    //当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
    public void recover(RemoteAccessException e) {
        System.out.println(e.getMessage());
    }
}
