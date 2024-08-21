package org.example.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.springboot.domain.vo.RoleVo;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.entity.Role;
import org.example.springboot.domain.dto.RoleDto;
import org.example.springboot.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色前端控制器
 * </p>
 */
@RestController
@RequestMapping("/role")
@Tag(name = "角色", description = "角色")
public class RoleController {
    @Resource
    private IRoleService roleService;

    /**
     * 添加、修改角色
     *
     * @param role 角色
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改角色", description = "添加、修改角色", method = "POST")
    public Result<Void> save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    /**
     * 删除角色
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除角色", description = "删除角色", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        roleService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询角色列表
     *
     * @param dto 角色
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询角色列表", description = "查询角色列表", method = "GET")
    public Result<List<RoleVo>> getList(RoleDto dto) {
        List<RoleVo> list = roleService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询角色分页
     *
     * @param dto 角色
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询角色分页", description = "查询角色分页", method = "GET")
    public Result<IPage<RoleVo>> getPage(RoleDto dto) {
        IPage<RoleVo> page = roleService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询角色
     *
     * @param dto 角色
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询角色", description = "查询角色", method = "GET")
    public Result<RoleVo> getOne(RoleDto dto) {
        RoleVo vo = roleService.getOne(dto);
        return Result.success(vo);
    }
}
