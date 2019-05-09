package com.springboot.bussiness.pojo.config;

import com.springboot.config.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Data
//@Component
//@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:config/redis.yml")
//@ConfigurationProperties(prefix = "redis")

/**
 * 好像用不着...
 */
public class RedisConfigBO {
    private int database;
    private String host;
    private int port;
    private String password;
    private int maxActive; //最大连接数
    private String maxWait;
    private int maxIdle;
    private String minIdle;
    private int timeout;
}
