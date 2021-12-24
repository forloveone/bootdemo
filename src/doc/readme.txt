   /*
     数据处理
            枚举
            Data属性处理 日期工具应该参考 joda
            xml
                XStream的东西可以扩展成为一个工具类 XStremTest
            Json
            String 正则表达式
            java 操作excel world File html
                    Jsoup java解析html文档的工具,可以用来做爬虫
                    数据加密解密
            项目间数据传输 验证apache 的httpclient 发送报文 接受报文的 ApacheClient

      http请求post,get(两个站点的通信)
            使用多线程并发请求另一个站点
            spring restTemplate,两个服务器之间的数据交换(java)

        多线程
        定时任务 	quartz 分布式定时任务处理?

        反射

        IO  非阻塞IO

        分布式日志 lmax无锁异步日志

        分布式session原理
     */
    /*
        日志 配置进阶(tdo)
        git 版本工具
        maven jar包管理工具
        mvc pojo进入代码时的参数校验
        mybatis
        redis
     */
    //安全scrity 选择
    //页面 bootstrap(样式和组件) + jquery(操作dom) 需要一个模板引擎(JSP或者其他)
    //db mysql(连接池用什么) + redis缓存  redis缓存和mysql的切换时机(优雅的代码)
    //缓存
    //RabitMQ quartz
    //jekuns 自动发布
    //sonar 代码审查
    //Junit 单元测试 PowerMock
    //springcloud
    //内部类的应用? com.dujinyue.class_relationship.Outer
    //execution 详解 复杂的aop能实现什么? aop 不能用拦截器吗? AspectJTest



       注解

       标识为Bean
           @Controller
           @Service
           @Component
           @Repository
           这4个是等价的

       属性注入
           @Value
               基本属性注入

       Bean的装配
           @Autowired
           按类型自动装配一个bean

           @Autowired
           @Qualifier("student")
           两个一起使用,是按名称注入

           @Resource
           按类型自动装配一个Bean
           @Resource(name="student")
           按名称装配bean

           @PostConstruct
           Bean初始化后执行,是注解在bean中的方法上的,bean初始化后,会调用这个方法
           @PreDestroy
           Bean销毁前执行

       AOP
           一般使用aspectJ的实现
           		execution([访问权限类型]  返回值类型  [全限定性类名] 方法名(参数名) [抛出异常类型])

           			[]中的东西可以省略
           			*表示0到多个任意字符
           			..用在参数中表示任意多个参数,用在包名后,表示当前包及其子包路径
           			+用在类名后面,表示当前类及其子类,用在接口后,表示当前接口,及其实现类

   springMVC
       使用ModelAndView可以使用转发和重定向
       转发:
           mv.setViewName("forward:test");
           return mv; //请求转发到其他Controller

           mv.setViewName("forward:/WEB-INF/views/hello.jsp");
           return mv; //请求转发到页面
       重定向:
           重定向到页面时,不能到WEB-inf下,因为这里面的页面都得经过controller才能访问,重定向本质是浏览器又发了一次请求...

           mv.setViewName("redirect:error.jsp");//重定向到页面

           mv.setViewName("redirect:test");//重定向到其他Controller

           在重定向时,请求参数是无法通过request的属性向下一级资源中传递的.
           可以通过下面的方式达到参数传递的目的:
               1.当返回的是ModeAndView 时,视图解析器会将Map的value放入到url后作为参数传递出去,所以无论什么类型都会变成string
               所以当重定向到页面时可以用${param.name} 形式接收.
               当重定向到其他Controller时,正常接收即可.

               当返回的是String
                   return "redirect:test"
                   把当前方法中添加参数Model model 使用model来添加参数,和ModeAndView类似.取数据时也类似.

               返回是void
                   只能用来重定向到页面,
                   response.sendRedirect(request.getContextPath()+"/show.jsp");
                   只能使用session来传递数据.

               2.也可以在重定向前把参数放入到HttpSession中,到跳转后的地方冲session中取出来.


       @ResponseBody
           定义在方法上,适用于ajax只返回数据,不跳转页面的情况

       @PathVariable
           用来接收路径参数

       @RequestBody
           允许request的参数在request体中,而不是直接连接到地址后面.
           放置在参数前

       @Transactional
           方法上,支持事务


       服务器端推送:
           早期解决方式是ajax向服务器轮询消息,使浏览器第一时间获得服务端消息,但是它大大增加了服务器压力.
           当客户端向服务器端发送消息,服务端会抓住这个请求不放,等有数据更新的时候才返回给客户端,
           当客户端接收到消息后,再向服务端发起请求,周而复始

           Servlet3.0异步方法特性.TODO
