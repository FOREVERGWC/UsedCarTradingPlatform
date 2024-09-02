package org.example.springboot.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot.service.IEmailService;
import org.example.springboot.service.cache.ICaptchaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceServiceImpl implements IEmailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private ICaptchaService captchaService;

    @Value("${spring.application.name}")
    private String application;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendRegisterCode(String email) {
        sendCodeByEmail(email, "【注册】", "您的验证码是：{}");
    }

    @Override
    public void sendResetPasswordCode(String email) {
        sendCodeByEmail(email, "【修改密码】", "您的验证码是：{}");
    }

    @Override
    public void sendCodeByEmail(String email, String subject, String content) {
        String code = RandomUtil.randomNumbers(4);
        captchaService.setCaptcha(email, code);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(username, application);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(StrUtil.format(content, code), true);
            javaMailSender.send(message);
            log.info("邮箱：{}验证码：{}发送成功！", email, code);
        } catch (Exception e) {
            log.error("邮箱：{}验证码：{}发送失败！错误信息：{}", email, code, e.toString());
        }
    }
}
