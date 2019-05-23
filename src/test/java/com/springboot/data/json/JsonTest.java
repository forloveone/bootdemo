package com.springboot.data.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
    public void before() {
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
        List<RequestPojo> me = json.fromJson(josn2, new TypeToken<List<RequestPojo>>() {
        }.getType());
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

        List<RequestPojo> list = new ArrayList<>();
        RequestPojo pojo2 = new RequestPojo();
        pojo2.setName("哈哈");
        pojo2.setAddress("test");
        list.add(pojo);
        list.add(pojo2);
        String test2 = mapper.writeValueAsString(list);
        System.out.println(test2);

        String tt = "{\"name\":\"kjjl\",\"address\":\"hahha\"}";
        RequestPojo pojo = mapper.readValue(tt, RequestPojo.class);
        System.out.println(pojo);

        String josn2 = "[{\"name\":\"kjjl\",\"address\":\"hahha\"},{\"name\":\"哈哈\",\"address\":\"test\"}]";
        //List 套 实体类
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, RequestPojo.class);
        List<RequestPojo> me = mapper.readValue(josn2, javaType);
        System.out.println("转集合me:" + me);
    }

    @Test
    public void jacksonXml() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String tests = xmlMapper.writeValueAsString(pojo);
        System.out.println(tests);

        String tt = "<RequestPojo><name>kjjl</name><address>hahha</address></RequestPojo>";
        RequestPojo po = xmlMapper.readValue(tt, RequestPojo.class);
        System.out.println(po);
    }

    @Test
    public void test22() {
        String json = "{\n" +
                "   \"query_block\": {\n" +
                "     \"select_id\": 1,\n" +
                "     \"cost_info\": {\n" +
                "       \"query_cost\": \"4.10\"\n" +
                "     },\n" +
                "     \"table\": {\n" +
                "       \"table_name\": \"student\",\n" +
                "       \"access_type\": \"ALL\",\n" +
                "       \"rows_examined_per_scan\": 31,\n" +
                "       \"rows_produced_per_join\": 31,\n" +
                "       \"filtered\": \"100.00\",\n" +
                "       \"cost_info\": {\n" +
                "         \"read_cost\": \"1.00\",\n" +
                "         \"eval_cost\": \"3.10\",\n" +
                "         \"prefix_cost\": \"4.10\",\n" +
                "         \"data_read_per_join\": \"36K\"\n" +
                "       },\n" +
                "       \"used_columns\": [\n" +
                "         \"id\",\n" +
                "         \"name\",\n" +
                "         \"age\"\n" +
                "       ]\n" +
                "     }\n" +
                "   }\n" +
                " }";
    }
}
