package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception{
        mailService.sendSimpleMail("chenxing@ecquaria.com","test simple mail","hello this is simple mail");
    }

    @Test
    public void testsendHtmlMail(){
        String content="<html>\n<body>\n<h3>hello world!这是一封html邮件</h3>" +
                "\n</body>\n</html>";
        mailService.sendHtmlMail("chenxing@ecquaria.com","test simple html mail",content);
    }

    @Test
    public void testsendAttachmentsMail(){
        String filePath="E:\\idea练习\\springDemo\\learning document\\spring-boot10.docx";
        mailService.sendAttachmentsMail("chenxing@ecquaria.com","test attachment mail","有附件，请查看",filePath);
    }

    @Test
    public void testsendInlineResourceMail(){
        String rscId="006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\'></body></html>";
        String imgPath="C:\\Users\\zhaoy\\Desktop\\system\\someProblem\\1.png";
        mailService.sendInlineResourceMail("chenxing@ecquaria.com","test InlineResourceMail mail",content,imgPath,rscId);
    }

    @Test
    public void sendTemplateMail(){//发送模板邮件，注意模板格式要正确（符合thymeleaf格式），否则会报模板找不到的错误
        Context context=new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("chenxing@ecquaria.com","主题：这是模板邮件",emailContent);
    }
}
