package com.springboot.third_party_library;

import com.google.gson.Gson;
import com.springboot.pojo.ReflactTest;
import com.springboot.pojo.Teacher;
import com.springboot.pojo.pojosort.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class AppacheCommonsTest {
    /**
     * 属性赋值
     */
    @Test
    public void beanUtils() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Teacher teacher = new Teacher();
        //给属性赋值
        BeanUtils.setProperty(teacher, "name", "tea");
        System.out.println(teacher);
        System.out.println(BeanUtils.getProperty(teacher, "name"));
    }

    /**
     * map to pojo pojo to map
     */
    @Test
    public void map2PojoAndPojo2Map() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Teacher teacher = new Teacher();
        teacher.setName("heh");
        teacher.setAge(11);
        //pojo to map
        Map<String, String> map = BeanUtils.describe(teacher);
        System.out.println(map);
        //map to pojo
        Teacher t2 = new Teacher();
        BeanUtils.populate(t2, map);
        System.out.println(t2);
    }

    /**
     * 　　为什么会有Base64编码呢？因为有些网络传送渠道并不支持所有的字节，例如传统的邮件只支持可见字符的传送，
     * 像ASCII码的控制字符就 不能通过邮件传送。这样用途就受到了很大的限制，比如图片二进制流的每个字节不可能全部是可见字符，
     * 所以就传送不了。最好的方法就是在不改变传统协议的情 况下，做一种扩展方案来支持二进制文件的传送。
     * 把不可打印的字符也能用可打印字符来表示，问题就解决了。
     * Base64编码应运而生，Base64就是一种 基于64个可打印字符来表示二进制数据的表示方法。
     */
    @Test
    public void base64() {
        String str = "和咯就看见了 test asdfb !@ 123";
        //加密
        byte[] bytes = Base64.encodeBase64(str.getBytes());
        System.out.println(new String(bytes));
        //解密
        String str2 = new String(Base64.decodeBase64("5ZKM5ZKv5bCx55yL6KeB5LqGIHRlc3QgYXNkZmIgIUAgMTIz"));
        System.out.println(str2);
    }

    /**
     * 信息摘要算法
     */
    @Test
    public void md5AndSha1() {
        String str = "和咯就看见了 test asdfb !@ 123";
        String st2 = DigestUtils.md5Hex(str);
        System.out.println(st2);

        String st3 = DigestUtils.sha1Hex(str);
        System.out.println(st3);
    }

    /**
     * 集合的交差并
     */
    @Test
    public void collec() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("1");
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        list2.add("3");
        list2.add("1");
        list2.add("4");

        boolean notEmpty = CollectionUtils.isNotEmpty(list1);
        System.out.println(notEmpty);
        boolean notEmpty1 = CollectionUtils.isNotEmpty(list3);
        System.out.println(notEmpty1);

        //并集
        List<String> union = (List<String>) CollectionUtils.union(list1, list2);
        System.out.println(union);

        //交集
        List<String> retainAll = (List<String>) CollectionUtils.retainAll(list1, list2);
        System.out.println(retainAll);

        //差集
        List<String> subtract = (List<String>) CollectionUtils.subtract(list1, list2);
        System.out.println(subtract);

        //对于pojo来说
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setAge(10);
        Person p2 = new Person();
        p2.setAge(11);
        Person p3 = new Person();
        p3.setAge(6);
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        List<Person> personList2 = new ArrayList<>();
        Person p4 = new Person();
        p4.setAge(1);
        personList2.add(p4);
        Person p5 = new Person();
        p5.setAge(6);
        personList2.add(p5);
        //并集
        List<Person> unionPersonList = (List<Person>) CollectionUtils.union(personList, personList2);
        System.out.println(unionPersonList);
        //交集
        List<Person> retainallList = (List<Person>) CollectionUtils.retainAll(personList, personList2);
        System.out.println(retainallList);
        //差集
        List<Person> subtractList = (List<Person>) CollectionUtils.subtract(personList, personList2);
        List<Person> subtractList2 = (List<Person>) CollectionUtils.subtract(personList2, personList);
        System.out.println(subtractList);
        System.out.println(subtractList2);
    }

    /**
     * IO
     */
    @Test
    public void io() throws IOException {
        InputStream in = new URL("http://www.baidu.com").openStream();
        try {
            String string = IOUtils.toString(in, "utf-8");
            System.out.println(string);
            // TODO 这个好像 in 被消费后没有了??
            List<String> list = IOUtils.readLines(in, "utf-8");
            for (String str : list) {
                System.out.println(str);
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 文件操作
     */
    @Test
    public void file() throws IOException {
        File file = new File("E:/test.txt");
        List<String> list = FileUtils.readLines(file, "gbk");
        for (String str : list) {
            System.out.println(str);
        }
        //字符串写入文件
//        FileUtils.writeStringToFile(file,"apache common file test","GBK",true);

        long freeSpaceKb = FileSystemUtils.freeSpaceKb("E:/");
        System.out.println(freeSpaceKb);
    }

    /**
     * string 处理
     */
    @Test
    public void string() {
        String str = "sas";
        //判断字符串为null,多个连续空格不为null
        StringUtils.isEmpty(str);
        //多个连续空格为null
        StringUtils.isBlank(str);
        //去除前后空格
        StringUtils.trim(str);
        //strip 开头的方法是trim的扩展,不局限于空白符
        String trim = StringUtils.strip(str, "s");
        System.out.println(trim);

        String[] strs = {"a", "b", "c"};
        String join = StringUtils.join(strs, "-");
        System.out.println(join);
    }

    /**
     * 随机数
     */
    @Test
    public void random() {
        //生成从0-10 不包括10的整数
        int i = RandomUtils.nextInt(0, 10);
        String random = RandomStringUtils.random(3);
        System.out.println(random);
        //随机生成字符串在指定范围内
        String random1 = RandomStringUtils.random(10, "1234567890abcdefghijklmnopqrstuvwxyz");
        System.out.println(random1);
    }

    @Test
    public void number() {
        int[] ints = {1, 2, 6, 33, 2, 1};
        int max = NumberUtils.max(ints);
        int min = NumberUtils.min(ints);
        System.out.println(max + " " + min);
    }

    /**
     * 日期
     */
    @Test
    public void date() {
        //截断日期到小时 后面补0
        Date truncate = DateUtils.truncate(new Date(), Calendar.HOUR);
        String time = DateFormatUtils.format(truncate, "yyyy-MM-dd HH:mm:ss.SSS");
        String time2 = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(time + " " + time2);
        String yyyyMMdd = DateFormatUtils.format(new Date(), "yyyyMMdd");
        System.out.println(yyyyMMdd);
        //计算明天的时间.
        Date date = DateUtils.addDays(new Date(), 1);
        String time3 = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(time3);
    }

    /**
     * 反射
     */
    @Test
    public void methodReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ReflactTest test = new ReflactTest();
        //调用实例方法
        MethodUtils.invokeMethod(test, "test", null);
        //调用静态方法
        MethodUtils.invokeStaticMethod(ReflactTest.class, "staticMethod", "methdo arg");
    }

    /**
     * http client get 无参数
     * 支持 HTTP 协议的客户端编程工具包
     */
    @Test
    public void httpGetAndPost() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer sb = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            sb.append("name=" + URLEncoder.encode("&", "utf-8"));
            sb.append("&");
            sb.append("age=24");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //无参数  HttpPost一样
        HttpGet httpGet = new HttpGet("http://localhost:9091/demo/testController/");
        //直接请求带参数
//        HttpGet httpGet = new HttpGet("http://localhost:9091/demo/testController/getParam" + "?" + sb);
        //可以根据path路径来得到参数
//        HttpGet httpGet = new HttpGet("http://localhost:9091/demo/testController/path/t1/tt1");

//        //Post请求 有参数 (支持普通参数,对象参数,对象参数和普通参数都有 等情况)
//        HttpPost httpPost = new HttpPost("http://localhost:12345/doPostControllerFour" + "?" + sb);
        HttpPost httpPost = new HttpPost("http://localhost:9091/demo/testController/testHttpclient"+"?"+"name=test");
        Gson gson = new Gson();
        com.springboot.bussiness.pojo.Person person = new com.springboot.bussiness.pojo.Person();
        person.setAddress("123");
        person.setAge("11");
        person.setName("a");
//        String jsonParam = "name=232&age=2&address=1";
        String jsonParam = gson.toJson(person);
        StringEntity entity = new StringEntity(jsonParam, "utf-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        //设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//        httpPost.setHeader("Content-Type", "text/html;charset=ISO-8859-1");

        // 响应
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();
            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 发送Get请求
            response = httpClient.execute(httpPost);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
