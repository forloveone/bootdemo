package com.springboot.bussiness.controller;

import com.springboot.bussiness.pojo.Person;
import com.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class TestController {

    //属性注入
    @Value("${my.name}")
    private String name;
    //属性注入Pojo
    @Autowired
    private Person person;

    @Autowired
//    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
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

    @RequestMapping("/test5")
    public ModelAndView test5(@Validated Person person, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        FieldError nameError = br.getFieldError("name");
        if (nameError != null) {
            mv.addObject("ex", nameError.getDefaultMessage());
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
}
