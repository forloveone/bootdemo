package com.test.cache;

import com.dujinyue.SpringBootRun;
import com.dujinyue.bussiness.pojo.Person;
import com.dujinyue.bussiness.pojo.TestPojo;
import com.dujinyue.utils.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringBootRun.class})
public class RedisTest {
    @Autowired
    private Person person;
    private TestPojo pojo;

    @Before
    public void init() {
        Person person = new Person();
        person.setAddress("中国");
        person.setAge("23");
        person.setName("王五");
        TestPojo pojo = new TestPojo();
        pojo.setAge(10);
        pojo.setBignum(new BigDecimal("10.9"));
        pojo.setDate(new Date());
        pojo.setName("test");
        pojo.setNum(10.23);
        person.setPojo(pojo);
        this.person = person;
        this.pojo = pojo;
    }

    /**
     * 存储String结构,value可以使pojo会自动成json串
     */
    @Test
    public void redisString() {
        boolean set = RedisUtil.set("123", person);
        System.out.println(set);
        Person test2 =  RedisUtil.get("123");
        System.out.println(test2);
        RedisUtil.set("test", "hello1");
        String he = RedisUtil.get("test");
        System.out.println(he);
    }

    @Test
    public void redisLock(){
        boolean ggstart = RedisUtil.setIfAbsent("ggstart", "分布式锁test", 100000);
        System.out.println(ggstart);
    }

    @Test
    public void unLock(){
        boolean b= RedisUtil.outLock("ggstart", "分布式锁test");
        String ggstart = RedisUtil.get("ggstart");
        System.out.println(ggstart);
        System.out.println(b);
    }

    /**
     * 存储pojo形式的  hash结构
     */
    @Test
    public void redisHash() {
        boolean hash_pojo = RedisUtil.hSetPojo("pojo", pojo);
        System.out.println(hash_pojo);
        TestPojo test = RedisUtil.hGetPojo("pojo", TestPojo.class);
        System.out.println(test);
    }

    @Test
    public void redisKeys() {
        Set<String> set = RedisUtil.getRedisTemplate().keys("123");
        System.out.println(set);
    }

    /**
     * List 操作
     */
    @Test
    public void redisList() {
        RedisUtil.lSet("list", person);
        RedisUtil.lSet("list", person);
        RedisUtil.lSet("list", person);
        RedisUtil.lSet("list", person);
        List<Person> list = RedisUtil.lGet("list", 0, -1);
        System.out.println();
    }

    /**
     * Set 操作
     */
    @Test
    public void redisSet() {
        long l = RedisUtil.sSet("set", person, person);
        System.out.println(l);

        Set<Person> set = RedisUtil.sGet("set");
        System.out.println(set);
    }
}
