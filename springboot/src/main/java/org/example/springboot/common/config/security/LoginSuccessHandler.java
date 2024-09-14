package org.example.springboot.common.config.security;

import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.instance.ObjectMapperInstance;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.service.ILogLoginService;
import org.example.springboot.utils.ServletUtils;
import org.example.springboot.utils.TokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户认证成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private ILogLoginService logLoginService;

    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        LoginUser user = Convert.convert(LoginUser.class, authentication.getPrincipal());

        // TODO 异步记录登录信息
        logLoginService.record(user.getId(), user.getUsername(), true, Result.success().getMsg());

        // TODO token存入Redis
        String token = TokenUtils.createToken(user.getId(), user.getUsername());
        user.setToken(token);
        ServletUtils.write(response, objectMapper.writeValueAsString(Result.success(user)));
    }
}
