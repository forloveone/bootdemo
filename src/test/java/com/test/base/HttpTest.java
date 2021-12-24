package com.test.base;

import com.dujinyue.SpringBootRun;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * java 访问 网络的模式
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRun.class )
public class HttpTest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * java api 原生模式
     */
    @Test
    public void api() {
        try {
            String urlString = "http://www.baidu.com";
            URL url = new URL(urlString); // 代表了一个网址
            InputStream is = url.openStream(); // 获得网页的内容
            // 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * apache httpclient 模式
     */
    @Test
    public void apache() {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
        System.out.println(httpGet.getRequestLine());
        try {
            // 执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 响应状态
            System.out.println("status:" + httpResponse.getStatusLine());
            // 判断响应实体是否为空
            if (entity != null) {
                System.out.println("contentEncoding:" + entity.getContentEncoding());
                System.out.println("response content:" + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * spring restTemplate 模式
     */
    @Test
    public void spring(){
        ResponseEntity<String> response = restTemplate.getForEntity("http://www.baidu.com", String.class);
        System.out.println(response.getBody());
    }
}