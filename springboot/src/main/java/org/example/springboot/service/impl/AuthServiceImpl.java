package org.example.springboot.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.domain.model.RegisterBody;
import org.example.springboot.domain.model.ResetBody;
import org.example.springboot.domain.vo.CaptchaVo;
import org.example.springboot.service.*;
import org.example.springboot.service.cache.ICaptchaService;
import org.example.springboot.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private AuthenticationManager authenticationManager;
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
//        validateCaptcha(username, captcha, uuid); // 校验验证码
//        loginPreCheck(username, password); // 密码前置校验
        // TODO: 2023/12/27 用户校验
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
//                sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("user.not.exists"));
                // TODO: 2024/1/2 异步记录日志
//                throw new UserNotExistsException();
            } else {
//                sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, e.getMessage());
//                throw new ServiceException(e.getMessage());
            }
            throw new RuntimeException(e);
        }
//        sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_SUCCESS, MessageUtils.getMsg("user.login.success"));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        sysUserService.recordLoginInfo(loginUser.getUserId());
        String token = TokenUtils.createToken(loginUser.getId(), loginUser.getUsername());
        loginUser.setToken(token);
        return loginUser;
//        User user = userService.getByUsername(body.getUsername());
//        if (user == null) {
//            throw new RuntimeException("用户不存在！");
//        }
//        if (!Objects.equals(body.getPassword(), user.getPassword())) {
//            throw new RuntimeException("用户名或密码错误！");
//        }
//        if (Objects.equals(user.getStatus(), UserStatus.DISABLE.getCode())) {
//            throw new RuntimeException("该用户已被禁用！请联系管理员");
//        }
//        captchaService.validateLoginCode(body.getUuid(), body.getCode());
//        // 生成token
//        String token = TokenUtils.createToken(user.getId(), user.getPassword());
//        UserVo vo = userService.getOne(UserDto.builder().id(user.getId()).build());
//        vo.setToken(token);
//        // TODO 异步记录登录信息
//        return vo;
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
