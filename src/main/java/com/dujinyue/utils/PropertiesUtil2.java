package com.dujinyue.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 应使用 springboot 的属性注入 不用这么麻烦了
 */
@Deprecated
public class PropertiesUtil2 {

    public String appKey = "";
    public String appMasterSecret = "";

    public String IOSappKey = "";
    public String IOSappMasterSecret = "";

    public String environment = "";

    public PropertiesUtil2()
    {
        // 读取配置
        Properties pp = null;
        InputStream fis = null;
        try {
            pp = new Properties();
            fis = this.getClass().getClassLoader().getResourceAsStream("resource/YM.properties");  //相对于src下的
            pp.load(fis);
            appKey = pp.getProperty("appKey");
            appMasterSecret = pp.getProperty("appMasterSecret");
            IOSappKey = pp.getProperty("IOSappKey");
            IOSappMasterSecret = pp.getProperty("IOSappMasterSecret");
            environment = pp.getProperty("environment");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            fis = null;
        }
    }
}
