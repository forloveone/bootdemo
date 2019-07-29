package com.springboot.bussiness.controller;

import com.google.gson.Gson;
import com.springboot.bussiness.pojo.TestPojo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//使用junit4进行测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置
@SpringBootTest
@WebAppConfiguration
@Transactional
public class JunitTestTest {
    // 模拟MVC对象
    private MockMvc mockMvc;

    private Gson gson = new Gson();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("结束");
    }

    @Test
    public void test1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/test/1")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void hasParam() throws Exception {
        //get put 都可以使这种参数
        MvcResult result = mockMvc.perform(get("/test/2?text=合肥"))
                //判断是否返回200,正确执行
                .andExpect(status().isOk())
                // 返回执行请求的结果
                .andReturn();
        System.out.println(result);
    }

    @Test
    public void hasPojo() throws Exception {
        TestPojo pojo = new TestPojo();
        pojo.setAge(11);
        pojo.setBignum(new BigDecimal("22.1"));
        //日期 做转化的时候有问题
//        pojo.setDate(new Date());
        pojo.setName("王五");
        pojo.setNum(11.2);


        MvcResult result = mockMvc.perform(post("/test/3").contentType(MediaType.APPLICATION_JSON).
                content(gson.toJson(pojo))).
                andExpect(status().isOk()).
                andReturn();
        System.out.println(result);
    }

    @Test
    public void hasReturn() throws Exception {
        //多参数
        MvcResult result = mockMvc.perform(post("/test/4")
                .param("param1", "temp")
                .param("param2", "temp2")).
                andExpect(status().isOk()).
                andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void mockReturn() throws Exception {
        JunitTest mock = Mockito.mock(JunitTest.class);
        when(mock.mock()).thenReturn("temp");
        System.out.println(mock.mock());

        MvcResult result = mockMvc.perform(post("/test/5")).
                andExpect(status().isOk()).
                andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}