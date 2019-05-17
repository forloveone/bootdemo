package com.springboot.data.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.springboot.pojo.RequestPojo;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    private RequestPojo pojo = new RequestPojo();

    @Before
    public void before(){
        pojo.setAddress("hahha");
        pojo.setName("kjjl");
    }

    /**
     * Gson 转换json
     * toJson
     * fromJson
     */
    @Test
    public void GsonTest() {
        Gson json = new Gson();
        String t = json.toJson(pojo);
        System.out.println(t);

        String test = "{\"name\":\"kjjl\",\"address\":\"hahha\"}";
        RequestPojo entity = json.fromJson(test, RequestPojo.class);
        System.out.println(entity);

        //List 套 实体类
        String josn2 = "[{\"name\":\"kjjl\",\"address\":\"hahha\"},{\"name\":\"哈哈\",\"address\":\"test\"}]";
        List<RequestPojo> me = json.fromJson(josn2,new TypeToken<List<RequestPojo>>(){}.getType());
        System.out.println("转集合me:" + me);
    }

    /**
     * JackSon
     * writeValueAsString
     * readValue
     */
    @Test
    public void jsckson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String test = mapper.writeValueAsString(pojo);
        System.out.println(test);

        List<RequestPojo> list  = new ArrayList<>();
        RequestPojo pojo2 = new RequestPojo();
        pojo2.setName("哈哈");
        pojo2.setAddress("test");
        list.add(pojo);
        list.add(pojo2);
        String test2 = mapper.writeValueAsString(list);
        System.out.println(test2);

        String tt = "{\"name\":\"kjjl\",\"address\":\"hahha\"}";
        RequestPojo pojo = mapper.readValue(tt,RequestPojo.class);
        System.out.println(pojo);

        String josn2 = "[{\"name\":\"kjjl\",\"address\":\"hahha\"},{\"name\":\"哈哈\",\"address\":\"test\"}]";
        //List 套 实体类
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, RequestPojo.class);
        List<RequestPojo> me = mapper.readValue(josn2, javaType);
        System.out.println("转集合me:" + me);
    }
}
