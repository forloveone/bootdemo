package com.test.mail;

import com.springboot.SpringBootRun;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringBootRun.class})
@TestPropertySource("classpath:/config/mail.properties")
public class JavaMailTest {
    @Value("${mailSender}")
    private String mailSender;
    @Value("${senderName}")
    private String senderName;
    @Value("${senderPassword}")
    private String senderPassword;

    @Test
    public void send() throws Exception {
        sendMail("1587007493@qq.com", "Java Mail 测试邮件", "<a>html 元素</a>：<b>哈哈哈2</b>");
    }

    /**
     * 发送邮件方法
     * @param receive 接收方
     * @param title   标题
     * @param content 内容
     * @throws Exception
     */
    private void sendMail(String receive, String title, String content) throws Exception {
        // 使用smtp：简单邮件传输协议
        Properties props = new Properties(); //可以加载一个配置文件
        props.put("mail.smtp.host", "smtp.163.com");//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证

        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        session.setDebug(true); //有他会打印一些调试信息。

        //设置信件内容
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(mailSender));//设置发件人的地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receive));//设置收件人,并设置其接收类型为TO
        message.setSubject(title);//设置标题
        //message.setText(mailContent); //发送纯文本邮件
        message.setContent(content, "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息

        //发送邮件
        Transport transport = session.getTransport("smtp");
        transport.connect(senderName, senderPassword);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }
}
