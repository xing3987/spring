package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MailServiceImpl implements MailService{

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override//发送简单的邮件
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        }catch (Exception e){
            logger.error("发送简单邮件时发生异常！",e);
        }
    }

    @Override//发送html格式的邮件
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            mailSender.send(message);
            logger.error("html邮件发送成功");
        }catch (Exception e){
            logger.error("发送html邮件时发生异常!",e);
        }
    }

    @Override//发送带附件的邮件
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content,true);

            //创建附件
            FileSystemResource file=new FileSystemResource(new File(filePath));
            System.out.println(file);
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,file);//添加附件，可以添加多个
            mailSender.send(message);
            logger.info("发送带附件的邮件");
        }catch (Exception e){
            logger.error("发送带附件的邮件是发生异常！",e);
        }
    }

    @Override//发送带静态资源的邮件（图片等）
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);//添加静态资源
            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        }catch (Exception e){
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}
