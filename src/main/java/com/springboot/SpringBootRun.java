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

    private void toDo() {
        /*
            集成mybatis 基本操作
                注解回滚
         */

        /*
            集成redis get 基本操作 配置详解
         */

        /*
            拦截器 和 Aop 的区别
         */

        /*
         数据处理
                Data属性处理 日期工具应该参考 joda
                        xml
                XStream的东西可以扩展成为一个工具类 XStremTest
                java 操作excel world File 等
                        数据加密解密
                项目间数据传输 验证apache 的httpclient 发送报文 接受报文的 ApacheClient
         */
    }

    private void 一个项目应该有的模块() {
        //日志get  配置进阶 分布式日志
        //安全 shiro 和 scrity 选择
        //页面 bootstrap + jquery
        //db mysql(连接池用什么) + redis缓存
        //缓存 用什么
        //rebittmq quartz
        //springcloud
    }

    private void question() {
        //内部类的应用? com.springboot.class_relationship.Outer
        //execution 详解 复杂的aop能实现什么? aop 不能用拦截器吗? AspectJTest
    }

    //    private void future() {
    //        //java 操作视频
    //    }
}
