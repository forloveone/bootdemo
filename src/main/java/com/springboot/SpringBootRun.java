package com.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBootApplication 注解集合了
 * 会自动扫描所在类的同级包及下级包的Bean
 * EnableAutoConfiguration注解   让项目根据类路径下的jar包依赖为当前项目进行自动配置
 * EnableScheduling 开启spring定时任务支持 和 Scheduled 配合使用
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootRun {

    public static void main(String[] args) {
//        SpringApplication.run(SpringBootRun.class, args);

        SpringApplication application = new SpringApplication(SpringBootRun.class);
        application.setBannerMode(Banner.Mode.OFF);//banner关闭
        application.run(args);
    }

    private void toDo(){
        //TODO springbootTest
        //看到ssmconfig项目中 TestControll test5
        //Trasa 注解回滚
        //集成mybatis 基本操作
        //集成redis 基本操作
        //TODO Volatile 可以修饰什么? 多线程
        //TODO 从Test项目中搬迁代码
    }
}

