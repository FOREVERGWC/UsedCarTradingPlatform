package org.example.springboot.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.springboot.common.Constants;
import org.example.springboot.common.instance.ObjectMapperInstance;
import org.example.springboot.domain.model.LoginBody;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * JSON账密登录
 */
@Component
public class JsonLoginFilter extends UsernamePasswordAuthenticationFilter {
    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();

    public JsonLoginFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String contentType = request.getContentType();

        if (!Objects.equals(HttpMethod.POST.name(), request.getMethod())) {
            throw new AuthenticationServiceException("不支持的请求方式：" + request.getMethod());
        }

        if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType) && !Constants.APPLICATION_JSON_UTF8.equalsIgnoreCase(contentType)) {
            return super.attemptAuthentication(request, response);
        }

        LoginBody body = objectMapper.readValue(request.getInputStream(), LoginBody.class);
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(body.getUsername(), body.getPassword());
        setDetails(request, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
