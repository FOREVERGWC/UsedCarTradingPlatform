package org.example.springboot.service.cache;

import cn.hutool.core.convert.Convert;
import jakarta.annotation.Resource;
import org.example.springboot.common.Constants;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.exception.CustomException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaServiceImpl implements ICaptchaService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setCaptcha(String uuid, String value) {
        String key = getKey(uuid);
        redisTemplate.opsForValue().set(key, value, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
    }

    @Override
    public String getCaptcha(String uuid) {
        String key = getKey(uuid);
        return Convert.toStr(redisTemplate.opsForValue().get(key), "");
    }

    @Override
    public void removeCaptcha(String uuid) {
        String key = getKey(uuid);
        redisTemplate.delete(key);
    }

    @Override
    public void validateLoginCode(String uuid, String code) {
        String captcha = getCaptcha(uuid);
        if (!Objects.equals(captcha, code)) {
            throw new CustomException(ResultCode.LOGIN_CODE_ERROR);
        }
        removeCaptcha(uuid);
    }

    @Override
    public void validateCode(String uuid, String code) {
        String captcha = getCaptcha(uuid);
        if (!Objects.equals(captcha, code)) {
            throw new CustomException(ResultCode.REGISTER_CODE_ERROR);
        }
        removeCaptcha(uuid);
    }

    private String getKey(String uuid) {
        return Constants.CAPTCHA_KEY + uuid;
    }
}
