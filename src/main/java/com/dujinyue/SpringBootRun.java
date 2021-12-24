package com.dujinyue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBootApplication 注解集合了
 * 会自动扫描所在类的同级包及下级包的Bean
 * EnableAutoConfiguration注解   让项目根据类路径下的jar包依赖为当前项目进行自动配置
 * EnableScheduling 开启spring定时任务支持 和 Scheduled 配合使用
 */
@SpringBootApplication
@EnableScheduling //定时任务支持
@EnableAspectJAutoProxy //aop注解支持
@EnableAsync //异步任务的支持
@MapperScan("com.springboot.bussiness.dao") //扫描mybatis的接口
@EnableRetry
@EnableTransactionManagement
public class SpringBootRun {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRun.class, args);
//        SpringApplication application = new SpringApplication(SpringBootRun.class);
//        application.setBannerMode(Banner.Mode.OFF);//banner关闭
//        application.run(args);
    }
}
