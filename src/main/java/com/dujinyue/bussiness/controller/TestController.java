package com.dujinyue.bussiness.controller;

import com.dujinyue.bussiness.pojo.Person;
import com.dujinyue.bussiness.pojo.TestPojo;
import com.dujinyue.bussiness.service.AsyncServiceTest;
import com.dujinyue.bussiness.service.PushService;
import com.dujinyue.events.EventsTest;
import com.dujinyue.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/testController")
public class TestController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    AsyncServiceTest asyncServiceTest;

    @Autowired
    PushService pushService;

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

    @RequestMapping("getParam")
    public void getParam(String name, int age) {
        System.out.println("姓名是" + name + "\n" + "年龄是" + age);
    }

    @RequestMapping("testHttpclient")
    public void testHttpclient(@RequestBody Person person, String name) {
        System.out.println(person);
    }
}
