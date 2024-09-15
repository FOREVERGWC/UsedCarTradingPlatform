package org.example.springboot.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.entity.system.User;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.domain.model.RegisterBody;
import org.example.springboot.domain.model.ResetBody;
import org.example.springboot.domain.vo.CaptchaVo;
import org.example.springboot.service.*;
import org.example.springboot.service.cache.ICaptchaService;
import org.example.springboot.strategy.LoginFactory;
import org.example.springboot.strategy.service.ILoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private LoginFactory loginFactory;
    @Resource
    private ICaptchaService captchaService;
    @Resource
    private IUserService userService;
    @Resource
    private IUserRoleLinkService userRoleLinkService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${captcha.enabled}")
    private Boolean captchaEnabled;

    @Override
    public CaptchaVo getCaptcha() {
        String uuid = UUID.fastUUID().toString();
        CaptchaVo vo = CaptchaVo.builder().uuid(uuid).enabled(captchaEnabled).build();
        if (!captchaEnabled) {
            return vo;
        }
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);
        String code = lineCaptcha.getCode();
        String img = lineCaptcha.getImageBase64Data();
        captchaService.setCaptcha(uuid, code);
        vo.setImg(img);
        return vo;
    }

    @Override
    public LoginUser login(LoginBody body) {
        ILoginService loginService = loginFactory.getFactory(body.getLoginType());
        return loginService.login(body);
    }

    @Transactional
    @Override
    public void register(RegisterBody body) {
        if (!Objects.equals(body.getPassword(), body.getConfirmPwd())) {
            throw new CustomException(ResultCode.REGISTER_CONFIRM_ERROR);
        }
        User user = User.builder().build();
        BeanUtils.copyProperties(body, user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        captchaService.validateCode(body.getEmail(), body.getCode());
        // TODO 2L替换为枚举
        userRoleLinkService.saveBatchByUserIdAndRoleIds(user.getId(), List.of(2L));
    }

    @Override
    public void resetPassword(ResetBody body) {
        if (!Objects.equals(body.getPassword(), body.getConfirmPwd())) {
            throw new CustomException(ResultCode.RESET_CONFIRM_ERROR);
        }
        captchaService.validateCode(body.getEmail(), body.getCode());
        User user = userService.getByEmail(body.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(body.getPassword()));
        userService.updateById(user);
    }
}
