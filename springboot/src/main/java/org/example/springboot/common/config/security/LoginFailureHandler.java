package org.example.springboot.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.instance.ObjectMapperInstance;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.service.ILogLoginService;
import org.example.springboot.utils.ServletUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户账户信息异常处理器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    // TODO 记录登录失败次数，短时间多次错误锁定账户
    @Resource
    private ILogLoginService logLoginService;

    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        LoginBody body = objectMapper.readValue(request.getInputStream(), LoginBody.class);
        String msg;
        switch (exception) {
            case AccountExpiredException ignored -> msg = ResultCode.LOGIN_ACCOUNT_EXPIRED_ERROR.getMsg();
            case CredentialsExpiredException ignored -> msg = ResultCode.LOGIN_CREDENTIALS_EXPIRED_ERROR.getMsg();
            case DisabledException ignored -> msg = ResultCode.LOGIN_DISABLED_ERROR.getMsg();
            case LockedException ignored -> msg = ResultCode.LOGIN_LOCKED_ERROR.getMsg();
            case BadCredentialsException ignored -> msg = ResultCode.LOGIN_USERNAME_OR_PASSWORD_ERROR.getMsg();
            case InternalAuthenticationServiceException e -> msg = e.getMessage();
            case null, default -> msg = ResultCode.LOGIN_USERNAME_OR_PASSWORD_ERROR.getMsg();

        }

        // TODO 重写登录逻辑，弃用默认的登录流程
//        // TODO 异步记录登录信息
        logLoginService.record(0L, body.getUsername(), false, msg);

        response.setStatus(HttpServletResponse.SC_OK);
        ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(msg)));

    }
}