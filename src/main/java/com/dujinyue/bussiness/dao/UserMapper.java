package com.dujinyue.bussiness.dao;

import com.dujinyue.bussiness.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> callTest();

    boolean batchInsert(List<User> list);
}