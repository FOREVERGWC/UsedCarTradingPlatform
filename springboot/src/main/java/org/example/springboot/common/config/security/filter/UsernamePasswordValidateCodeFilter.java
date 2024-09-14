package org.example.springboot.common.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.config.security.LoginFailureHandler;
import org.example.springboot.common.instance.ObjectMapperInstance;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.service.cache.ICaptchaService;
import org.example.springboot.utils.ValidateUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * 验证码过滤器
 */
@Component
public class UsernamePasswordValidateCodeFilter extends OncePerRequestFilter {
    @Resource
    private ICaptchaService captchaService;
    @Resource
    private LoginFailureHandler loginFailureHandler;

    public final String WEB_LOGIN_URL = "/login";

    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (!Objects.equals(uri, WEB_LOGIN_URL)) {
            filterChain.doFilter(request, response);
            return;
        }
        CachedBodyHttpServletRequest cachedBodyRequest = new CachedBodyHttpServletRequest(request);
        LoginBody body = objectMapper.readValue(cachedBodyRequest.getInputStream(), LoginBody.class);

        try {
            ValidateUtils.valid(body);
        } catch (InternalAuthenticationServiceException e) {
            loginFailureHandler.onAuthenticationFailure(cachedBodyRequest, response, e);
            return;
        }
        captchaService.validateLoginCode(body.getUuid(), body.getCode());
        filterChain.doFilter(cachedBodyRequest, response);
    }
}
