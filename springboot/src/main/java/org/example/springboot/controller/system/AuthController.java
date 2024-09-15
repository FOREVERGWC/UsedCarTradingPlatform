package org.example.springboot.controller.system;

import jakarta.annotation.Resource;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.model.*;
import org.example.springboot.domain.vo.CaptchaVo;
import org.example.springboot.service.IAuthService;
import org.example.springboot.service.IEmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.springboot.utils.UserUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站服务前端控制器
 * </p>
 */
@RestController
@Tag(name = "网站服务", description = "网站服务")
public class AuthController {
    @Resource
    private IAuthService authService;
    @Resource
    private IEmailService emailService;

    /**
     * PC端登录
     *
     * @param body PC端登录请求体
     * @return 结果
     */
    @PostMapping("/login")
    @Operation(summary = "PC端登录", description = "PC端登录", method = "POST")
    public Result<LoginUser> login(@Validated @RequestBody LoginBody body) {
        LoginUser user = authService.login(body);
        return Result.success(user);
    }

    /**
     * 注册用户
     *
     * @param body PC端注册请求体
     * @return 结果
     */
    @PostMapping("/register")
    @Operation(summary = "注册用户", description = "注册用户", method = "POST")
    public Result<Void> register(@Validated @RequestBody RegisterBody body) {
        authService.register(body);
        return Result.success();
    }

    /**
     * 重置密码
     *
     * @param body 密码信息
     * @return 结果
     */
    @PutMapping("/password/reset")
    @Operation(summary = "重置密码", description = "重置密码", method = "PUT")
    public Result<Void> resetPassword(@Validated @RequestBody ResetBody body) {
        authService.resetPassword(body);
        return Result.success();
    }

    /**
     * 获取验证码图片
     *
     * @return 结果
     */
    @GetMapping("/captcha")
    @Operation(summary = "获取验证码图片", description = "获取验证码图片", method = "GET")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo vo = authService.getCaptcha();
        return Result.success(vo);
    }

    /**
     * 获取当前用户信息
     *
     * @return 结果
     */
    @GetMapping("/token")
    @Operation(summary = "获取当前用户信息", description = "获取当前用户信息", method = "GET")
    public Result<LoginUser> getByToken() {
        LoginUser user = UserUtils.getLoginUser();
        return Result.success(user);
    }

    /**
     * 根据邮箱发送注册验证码邮件
     *
     * @param body 注册邮箱
     * @return 结果
     */
    @PostMapping("/register/code")
    @Operation(summary = "根据邮箱发送注册验证码邮件", description = "根据邮箱发送注册验证码邮件", method = "POST")
    public Result<Void> sendRegisterCode(@Validated @RequestBody EmailBody body) {
        emailService.sendRegisterCode(body.getEmail());
        return Result.success();
    }

    /**
     * 根据邮箱发送改密验证码邮件
     *
     * @param body 注册邮箱
     * @return 结果
     */
    @PostMapping("/reset/code")
    @Operation(summary = "根据邮箱发送改密验证码邮件", description = "根据邮箱发送改密验证码邮件", method = "POST")
    public Result<Void> sendResetPasswordCode(@Validated @RequestBody EmailBody body) {
        emailService.sendResetPasswordCode(body.getEmail());
        return Result.success();
    }
}
