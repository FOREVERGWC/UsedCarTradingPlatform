package org.example.springboot.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springboot.domain.model.LoginUser;

/**
 * <p>
 * 令牌服务类
 * </p>
 */
public interface ITokenService {
    /**
     * 根据请求获取令牌
     *
     * @param request 请求对象
     * @return 令牌
     */
    String getAuthorization(HttpServletRequest request);

    /**
     * 根据请求获取登录用户
     *
     * @param request 请求对象
     * @return 登录用户
     */
    LoginUser getLoginUser(HttpServletRequest request);
}
