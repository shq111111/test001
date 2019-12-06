package com.xd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author shq
 * @create 2019-12-06-9:48
 */
@Controller
public class sendMailController {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @RequestMapping("sendmail")
    @ResponseBody
    public String sendMail(MultipartFile fujian){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("这是一封测试邮件");
            helper.setText("spring 测试邮件发送");
            helper.addAttachment(fujian.getOriginalFilename(),fujian);
            helper.setSentDate(new Date());
            helper.setTo(new String("244110171@qq.com"));
            mailSender.send(message);
            return "success";
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
