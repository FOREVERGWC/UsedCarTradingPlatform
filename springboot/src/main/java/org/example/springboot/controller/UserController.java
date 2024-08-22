package org.example.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.dto.UserDto;
import org.example.springboot.domain.vo.UserVo;
import org.example.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户信息", description = "用户信息")
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 添加、修改用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改用户信息", description = "添加、修改用户信息", method = "POST")
    public Result<Void> save(@RequestBody UserDto dto) {
        userService.saveOrUpdate(dto);
        return Result.success();
    }

    /**
     * 删除用户信息
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除用户信息", description = "删除用户信息", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询用户信息列表
     *
     * @param dto 用户信息
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询用户信息列表", description = "查询用户信息列表", method = "GET")
    public Result<List<UserVo>> getList(UserDto dto) {
        List<UserVo> list = userService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询用户信息分页
     *
     * @param dto 用户信息
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询用户信息分页", description = "查询用户信息分页", method = "GET")
    public Result<IPage<UserVo>> getPage(UserDto dto) {
        IPage<UserVo> page = userService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询用户信息", description = "查询用户信息", method = "GET")
    public Result<UserVo> getOne(UserDto dto) {
        UserVo vo = userService.getOne(dto);
        return Result.success(vo);
    }

    /**
     * 解禁或禁用用户
     *
     * @param id 用户ID
     * @return 结果
     */
    @PutMapping("/status/{id}")
    @Operation(summary = "解禁或禁用用户", description = "解禁或禁用用户", method = "PUT")
    public Result<Void> handleStatus(@PathVariable Long id) {
        userService.handleStatus(id);
        return Result.success();
    }
}
