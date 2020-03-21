package com.xsh.test;

import com.xsh.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${mail.receiveMail.addr}")
    private String receive;

    @Test
    public void testSimpleMail() throws Exception {
        //纯文本邮件
        mailService.sendSimpleMail(receive,"test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        //html邮件
        String content="<html>\n" +
                "<body>\n" +
                "    <h3 style=\"background-color:red\">hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(receive,"test Html mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        //带附件的邮件，文件b.txt需存在
        String filePath="D:\\b.txt";
        mailService.sendAttachmentsMail(receive, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        /*带图片的邮件，rscId为图片添加的额外属性*/
        String rscId = "test";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\Administrator\\Desktop\\myblog\\图片管理\\firstPicture\\blogFirstPicture10.jpg";

        mailService.sendInlineResourceMail(receive, "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        //使用html模板邮件
        //创建邮件正文
        Context context = new Context();
        //设置参数，该参数可在邮件内显示
        context.setVariable("id", "001");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(receive,"主题：这是模板邮件",emailContent);
    }

    @Test
    public void asynSendTemplateMail() {
        //异步发送邮件
        Executor executor = Executors.newFixedThreadPool(10);
        //创建邮件正文
        Context context = new Context();
        //设置参数，该参数可在邮件内显示
        context.setVariable("id", "001");
        String emailContent = templateEngine.process("emailTemplate", context);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    mailService.sendHtmlMail(receive,"主题：这是模板邮件",emailContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executor.execute(task);
    }
}
