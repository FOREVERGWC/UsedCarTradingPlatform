package org.example.springboot.service;

import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.domain.model.RegisterBody;
import org.example.springboot.domain.model.ResetBody;
import org.example.springboot.domain.vo.CaptchaVo;

public interface IAuthService {
    /**
     * 获取验证码图片
     *
     * @return 结果
     */
    CaptchaVo getCaptcha();

    /**
     * PC端登录
     *
     * @param body PC端登录请求体
     * @return 结果
     */
    LoginUser login(LoginBody body);

    /**
     * 注册用户
     *
     * @param body PC端注册请求体
     */
    void register(RegisterBody body);

    /**
     * 重置密码
     *
     * @param body 密码信息
     */
    void resetPassword(ResetBody body);
}
