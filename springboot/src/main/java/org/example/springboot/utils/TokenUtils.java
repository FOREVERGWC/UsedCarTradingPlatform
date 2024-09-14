package org.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.exception.CustomException;

import java.util.Date;

/**
 * 令牌工具类
 */
public class TokenUtils {
    private static final String secretKey = "aasfasfasfasfasgmkgiosufaokm,yrdfl;.h";
    private static final String subject = "博客管理系统";

    /**
     * 生成令牌
     *
     * @param userId   用户ID
     * @param username 用户名
     * @return 令牌
     */
    public static String createToken(Long userId, String username) {
        return JWT.create()
                .withAudience(String.valueOf(userId), username)
                .withIssuedAt(new Date())
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .withSubject(subject)
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 校验令牌
     *
     * @param token 令牌
     */
    public static void verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new CustomException(ResultCode.TOKEN_VERIFY_ERROR);
        }
    }

    /**
     * 通过令牌获取用户ID
     *
     * @param token 令牌
     * @return 用户ID
     */
    public static Long getUserIdByToken(String token) {
        // TODO 根据令牌从缓存查询用户ID
        return Long.valueOf(JWT.decode(token).getAudience().getFirst());
    }

    /**
     * 通过令牌获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameByToken(String token) {
        // TODO 根据令牌从缓存查询用户名
        return JWT.decode(token).getAudience().getLast();
    }
}

