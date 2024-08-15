package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.BaseContext;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.dto.PasswordDto;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站服务前端控制器
 * </p>
 */
@RestController
@Tag(name = " 网站服务", description = " 网站服务")
public class WebController {
    @Resource
    private IUserService userService;

    /**
     * PC端登录
     *
     * @param loginBody PC端登录请求体
     * @return 结果
     */
    @PostMapping("/login")
    @Operation(summary = "PC端登录", description = "PC端登录", method = "POST")
    public Result<User> login(@RequestBody LoginBody loginBody) {
        User user = userService.login(loginBody);
        return Result.success(user);
    }

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/register")
    @Operation(summary = "注册用户", description = "注册用户", method = "POST")
    public Result<Void> register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param dto 密码信息
     * @return 结果
     */
    @PutMapping("/password/update")
    @Operation(summary = "修改密码", description = "修改密码", method = "PUT")
    public Result<Void> updatePassword(@RequestBody PasswordDto dto) {
        userService.updatePassword(dto);
        return Result.success();
    }

    /**
     * 获取当前用户信息
     *
     * @return 结果
     */
    @GetMapping("/token")
    @Operation(summary = "获取当前用户信息", description = "获取当前用户信息", method = "GET")
    public Result<User> getByToken() {
        User account = BaseContext.getUser();
        User user = userService.getById(account.getId());
        user.setToken(account.getToken());
        return Result.success(user);
    }
}
