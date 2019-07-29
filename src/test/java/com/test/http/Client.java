package com.test.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Client {
    public static void main(String[] args) {

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
}
