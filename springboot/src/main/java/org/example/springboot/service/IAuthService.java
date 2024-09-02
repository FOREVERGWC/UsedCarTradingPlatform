package org.example.springboot.service;

import org.example.springboot.domain.vo.CaptchaVo;

public interface IAuthService {
    /**
     * 获取验证码图片
     *
     * @return 结果
     */
    CaptchaVo getCaptcha();
}
