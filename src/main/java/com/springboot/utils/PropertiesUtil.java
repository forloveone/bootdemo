package com.springboot.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 应使用 springboot 的属性注入 不用这么麻烦了
 */
@Deprecated
public class PropertiesUtil {
    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}
