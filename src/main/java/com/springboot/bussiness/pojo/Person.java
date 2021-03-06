package com.springboot.bussiness.pojo;

import com.springboot.config.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
//@PropertySource("classpath:config/my.properties") //PropertySource注解目前只支持properties,直接写在application.yml中可以不用使用这个
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:config/my.yml")
@ConfigurationProperties(prefix = "my")
public class Person implements Serializable {
    private String name;
    private String age;
    private String address;

    private TestPojo pojo;
}
