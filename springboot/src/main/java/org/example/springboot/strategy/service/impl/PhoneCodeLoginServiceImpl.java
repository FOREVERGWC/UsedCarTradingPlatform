package org.example.springboot.strategy.service.impl;

import jakarta.annotation.Resource;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.service.cache.ICaptchaService;
import org.example.springboot.strategy.service.ILoginService;
import org.example.springboot.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 手机验证码登录服务类
 * </p>
 */
@Service
public class PhoneCodeLoginServiceImpl implements ILoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private ICaptchaService captchaService;

    @Override
    public LoginUser login(LoginBody body) {
        captchaService.validateLoginCode(body.getUuid(), body.getCode());
        // TODO 继承AbstractAuthenticationToken，重写一个PhoneCodeAuthenticationToken
        // TODO 注入PhoneCodeManager
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getPhone(), body.getCode());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            // TODO 登录失败异步记录日志
            // TODO 记录错误账号和错误次数存入Redis，短时间到达5次锁定账号
            throw new RuntimeException(e);
        }
        LoginUser user = (LoginUser) authentication.getPrincipal();
        String token = TokenUtils.createToken(user.getId(), user.getUsername());
        user.setToken(token);
        // TODO 校验验证码
        // TODO 校验是否锁定
        // TODO 校验是否禁用
        // TODO 创建token
        // TODO 异步记录登录日志
        // TODO 返回用户信息
        return user;
    }
}
