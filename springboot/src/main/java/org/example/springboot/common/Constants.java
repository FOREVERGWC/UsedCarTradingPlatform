package org.example.springboot.common;

public interface Constants {
    /**
     * 令牌
     */
    String TOKEN = "token";
    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";
    /**
     * Redis验证码键值前缀
     */
    String CAPTCHA_KEY = "captcha:";
    /**
     * Redis验证码有效时长
     */
    Long CAPTCHA_EXPIRATION = 2L;
    /**
     * UTF-8编码JSON请求体
     */
    String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
}
