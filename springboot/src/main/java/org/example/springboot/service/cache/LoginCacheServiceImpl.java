package org.example.springboot.service.cache;

import cn.hutool.core.convert.Convert;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginCacheServiceImpl implements ILoginCacheService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addFailureCount(String principal) {
        String key = "login:count:" + principal;
        Long count = redisTemplate.opsForValue().increment(key);
        if (Objects.equals(1L, count)) {
            redisTemplate.expire(key, 24, TimeUnit.HOURS);
        }
    }

    @Override
    public boolean getAccountNonLocked(String principal) {
        String key = "login:count:" + principal;
        Object value = redisTemplate.opsForValue().get(key);
        Integer count = Convert.toInt(value, 0);
        return count < 5;
    }
}
