package com.springboot.bussiness.controller;

import com.springboot.aop.AopBeforeAnnotation;
import com.springboot.bussiness.pojo.Person;
import com.springboot.bussiness.pojo.TestPojo;
import com.springboot.bussiness.service.AsyncServiceTest;
import com.springboot.bussiness.service.MyBatisService;
import com.springboot.bussiness.service.PushService;
import com.springboot.events.EventsTest;
import com.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/testController") //映射此类的访问路径是/testController
public class TestController {

    //属性注入
    @Value("${my.name}")
    private String name;
    //属性注入Pojo
    @Autowired
    private Person person;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    AsyncServiceTest asyncServiceTest;

    @Autowired
    PushService pushService;

    @Autowired
    private MyBatisService myBatisService;

//    @Autowired
//    private RedisTemplate redisTemplate;

    //produces 可以定制返回的response的媒体类型和字符集
    @RequestMapping(value = "/", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @AopBeforeAnnotation(name = "基于注解的aop")
    public String home() {
        System.out.println(name);
        return "Hello World!";
    }

    @RequestMapping("/testError")
    public void error() throws Exception {
        throw new Exception("erro");
    }

    @RequestMapping("/forward")
    public ModelAndView testForward() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:testError");
//        mv.setViewName("forward:/WEB-INF/views/hello.jsp");
        return mv;
    }

    @AopBeforeAnnotation(name = "基于注解的aop")
    @RequestMapping("/test5")
    public ModelAndView test5(@Validated Person person, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        List<ObjectError> allErrors = br.getAllErrors();
        //没有错误
        if (allErrors.size() == 0) {
            //还没有页面 ,或者数据处理
            mv.setViewName("Validate pass");
        } else {
            String errors = allErrors.toString();
            mv.addObject("ex", errors);
            mv.setViewName("error");
        }
        return mv;
    }

    /**
     * 文件上传
     * 传相同的文件名会把原先的覆盖掉
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpSession session) throws IOException {
        String originalFilename = file.getOriginalFilename();

        String serverPath = session.getServletContext().getRealPath("fileUpload");
        String filePath = getYearMonthDayFilePath();

        File newFile = new File(serverPath + File.separator + filePath, originalFilename);
        file.transferTo(newFile);
    }

    // 把上传文件按照年份,月份,日来区分
    private String getYearMonthDayFilePath() {
        String[] s = DateUtil.getShortDate(new Date()).split("-");
        return s[0] + File.separator + s[1] + File.separator + s[2];
    }


    @RequestMapping("events")
    public void events() {
        String msg = "hello";
        //事件发布
        applicationContext.publishEvent(new EventsTest(this, msg));
    }

    @RequestMapping("asyncMethod")
    public void testAsync() {
        //如果i很大比如100 会报异常 ? 原因是什么? TaskRejectedException 拒绝
        for (int i = 0; i < 100; i++) {
            asyncServiceTest.asyncMethodOne(i);
            asyncServiceTest.asyncMethodTwo(i);
        }
    }

    @RequestMapping(value = "/path/{s1}/{s2}")
    public void pathParam(@PathVariable String s1, @PathVariable String s2) {
        System.out.println(s1 + "   " + s2);
    }

    @RequestMapping(value = {"/method1", "/method2"})
    public void testTwo4One() {
        System.out.println("testTwo4One");
    }

    //想要支持直接返回成xml格式,需要jackson-dataformat-xml 的依赖
    @RequestMapping(value = "/xml", produces = "application/xml;charset=UTF-8")
    @ResponseBody
    public TestPojo responseXml() {
        return new TestPojo(123, 123.12, "wangwu", new Date(), new BigDecimal("11.1"));
    }

    //默认返回的就是json 格式的数据
    @RequestMapping(value = "/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public TestPojo responseJson() {
        return new TestPojo(11, 11.1, "wangwu", new Date(), new BigDecimal("11.1"));
    }

    @RequestMapping("/defer")
    public DeferredResult<String> deferredCall() {
        return pushService.getAsyncUpdate();
    }

    //对于非幂等的请求（比如新增，更新操作），千万不要使用重试
    @RequestMapping("retry")
    public void retry(){
        myBatisService.retryTest();
    }
}
