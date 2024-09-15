package org.example.springboot.strategy.service.impl;

import jakarta.annotation.Resource;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.service.ILogLoginService;
import org.example.springboot.service.cache.ICaptchaService;
import org.example.springboot.service.cache.ILoginCacheService;
import org.example.springboot.strategy.service.ILoginService;
import org.example.springboot.utils.TokenUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账密登录服务类
 * </p>
 */
@Service
public class UsernamePasswordLoginServiceImpl implements ILoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private ICaptchaService captchaService;
    @Resource
    private ILogLoginService logLoginService;
    @Resource
    private ILoginCacheService loginCacheService;

    @Override
    public LoginUser login(LoginBody body) {
        Authentication authentication;
        boolean flag = false;
        Exception exception = new RuntimeException();
        try {
            captchaService.validateLoginCode(body.getUuid(), body.getCode());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
            flag = true;
        } catch (AccountExpiredException e) {
            // 账号过期
            exception = e;
            throw e;
        } catch (CredentialsExpiredException e) {
            // 密码过期
            exception = e;
            throw e;
        } catch (DisabledException e) {
            // 账号被禁用
            exception = e;
            throw e;
        } catch (LockedException e) {
            // 账号被锁定
            exception = e;
            throw e;
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            // 用户名或密码错误
            exception = e;
            throw e;
        } catch (InternalAuthenticationServiceException e) {
            // 系统内部错误
            exception = e;
            throw e;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            // TODO 登录异步记录日志
            if (flag) {
                logLoginService.record(0L, body.getUsername(), true, Result.success().getMsg());
            } else {
                loginCacheService.addFailureCount(body.getUsername());
                logLoginService.record(0L, body.getUsername(), false, exception.getMessage());
            }
        }
        LoginUser user = (LoginUser) authentication.getPrincipal();
        String token = TokenUtils.createToken(user.getId(), user.getUsername());
        user.setToken(token);
        return user;
    }
}
