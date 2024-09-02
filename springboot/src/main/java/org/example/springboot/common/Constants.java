package org.example.springboot.common;

public interface Constants {
    String TOKEN = "token";
    /**
     * Redis验证码键值前缀
     */
    String CAPTCHA_KEY = "captcha:";
    /**
     * Redis验证码有效时长
     */
    Long CAPTCHA_EXPIRATION = 2L;
}
