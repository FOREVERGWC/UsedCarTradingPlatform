package org.example.springboot.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.instance.ObjectMapperInstance;
import org.example.springboot.domain.Result;
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
    // TODO 异步记录登录日志
    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        switch (exception) {
            case AccountExpiredException ignored ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(ResultCode.LOGIN_ACCOUNT_EXPIRED_ERROR)));
            case CredentialsExpiredException ignored ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(ResultCode.LOGIN_CREDENTIALS_EXPIRED_ERROR)));
            case DisabledException ignored ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(ResultCode.LOGIN_DISABLED_ERROR)));
            case LockedException ignored ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(ResultCode.LOGIN_LOCKED_ERROR)));
            case BadCredentialsException ignored ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(ResultCode.LOGIN_USERNAME_OR_PASSWORD_ERROR)));
            case InternalAuthenticationServiceException e ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(e.getMessage())));
            case null, default ->
                    ServletUtils.write(response, objectMapper.writeValueAsString(Result.error(ResultCode.LOGIN_USERNAME_OR_PASSWORD_ERROR)));
        }
    }
}
