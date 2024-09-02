package org.example.springboot.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.example.springboot.domain.vo.CaptchaVo;
import org.example.springboot.service.IAuthService;
import org.example.springboot.service.cache.ICaptchaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private ICaptchaService captchaService;

    @Value("${captcha.enabled}")
    private Boolean captchaEnabled;

    @Override
    public CaptchaVo getCaptcha() {
        String uuid = UUID.fastUUID().toString();
        CaptchaVo vo = CaptchaVo.builder().uuid(uuid).enabled(captchaEnabled).build();
        if (!captchaEnabled) {
            return vo;
        }
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);
        String code = lineCaptcha.getCode();
        String img = lineCaptcha.getImageBase64Data();
        captchaService.setCaptcha(uuid, code);
        vo.setImg(img);
        return vo;
    }
}
