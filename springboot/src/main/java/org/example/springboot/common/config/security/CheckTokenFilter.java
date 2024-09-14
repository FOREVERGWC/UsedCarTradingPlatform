package org.example.springboot.common.config.security;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.service.ITokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

/**
 * 令牌过滤器
 */
@Component
public class CheckTokenFilter extends OncePerRequestFilter {
    @Resource
    private ITokenService tokenService;

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    String[] whiteList = {
            "/captcha", "/register/code", "/reset/code",
            "/login", "/login/wechat", "/register", "/password/reset",
            "/static/**", "/file/**",
            "/doc.html", "/favicon.ico", "/webjars/**", "/swagger-resources", "/v3/api-docs/**"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (Arrays.stream(whiteList).anyMatch(url -> PATH_MATCHER.match(url, uri))) {
            filterChain.doFilter(request, response);
            return;
        }
        LoginUser user = tokenService.getLoginUser(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
