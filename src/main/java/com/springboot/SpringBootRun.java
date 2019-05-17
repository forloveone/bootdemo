package com.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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
                枚举
                Data属性处理 日期工具应该参考 joda
                xml
                    XStream的东西可以扩展成为一个工具类 XStremTest
                Json
                String
                java 操作excel world File 等
                        数据加密解密
                项目间数据传输 验证apache 的httpclient 发送报文 接受报文的 ApacheClient

          http请求post,get(两个站点的通信)
                使用多线程并发请求另一个站点
                spring restTemplate,两个服务器之间的数据交换(java)
         */
        /*
            多线程
            定时任务 	quartz 分布式定时任务处理?
         */
        /*
            反射
         */
        /*
            IO
         */
        /*
            分布式日志 lmax无锁异步日志
         */
        /*
            分布式session原理
         */
        /*
            批处理的概念,是一次执行多条语句吗?
         */
    }

    private void 存在特性() {
        /*
            日志 配置进阶(tdo)
            git 版本工具
            maven jar包管理工具
            mvc pojo进入代码时的参数校验
         */
    }

    private void 一个项目应该有的模块() {
        //安全 shiro 和 scrity 选择
        //页面 bootstrap(样式和组件) + jquery(操作dom) 需要一个模板引擎(JSP或者其他)
        //db mysql(连接池用什么) + redis缓存  redis缓存和mysql的切换时机(优雅的代码)
        //缓存
        //RabitMQ quartz
        //jekuns 自动发布
        //sonar 代码审查

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
