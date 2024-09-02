package org.example.springboot.service;

public interface IEmailService {
    /**
     * 根据邮箱发送注册验证码邮件
     *
     * @param email 邮箱
     */
    void sendRegisterCode(String email);

    /**
     * 根据邮箱发送改密验证码邮件
     *
     * @param email 邮箱
     */
    void sendResetPasswordCode(String email);

    /**
     * 根据邮箱发送验证码邮件
     *
     * @param email   邮箱
     * @param subject 主题
     * @param content 内容
     */
    void sendCodeByEmail(String email, String subject, String content);
}
