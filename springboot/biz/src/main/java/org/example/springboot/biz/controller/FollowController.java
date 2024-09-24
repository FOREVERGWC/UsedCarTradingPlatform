package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.system.domain.Result;
import org.example.springboot.biz.domain.dto.FollowDto;
import org.example.springboot.biz.domain.entity.Follow;
import org.example.springboot.biz.domain.vo.FollowVo;
import org.example.springboot.biz.service.IFollowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关注前端控制器
 * </p>
 */
@RestController
@RequestMapping("/follow")
@Tag(name = "关注", description = "关注")
public class FollowController {
    @Resource
    private IFollowService followService;

    /**
     * 添加、修改关注
     *
     * @param follow 关注
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改关注", description = "添加、修改关注", method = "POST")
    public Result<Void> save(@RequestBody Follow follow) {
        followService.saveOrUpdate(follow);
        return Result.success();
    }

    /**
     * 删除关注
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除关注", description = "删除关注", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        followService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询关注列表
     *
     * @param dto 关注
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询关注列表", description = "查询关注列表", method = "GET")
    public Result<List<FollowVo>> getList(FollowDto dto) {
        List<FollowVo> list = followService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询关注分页
     *
     * @param dto 关注
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询关注分页", description = "查询关注分页", method = "GET")
    public Result<IPage<FollowVo>> getPage(FollowDto dto) {
        IPage<FollowVo> page = followService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询关注
     *
     * @param dto 关注
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询关注", description = "查询关注", method = "GET")
    public Result<FollowVo> getOne(FollowDto dto) {
        FollowVo vo = followService.getOne(dto);
        return Result.success(vo);
    }

    /**
     * 查询粉丝数量和关注数量
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/info/{userId}")
    @Operation(summary = "查询粉丝数量和关注数量", description = "查询粉丝数量和关注数量", method = "GET")
    public Result<Map<String, Object>> getInfo(@PathVariable Long userId) {
        Map<String, Object> info = followService.getInfo(userId);
        return Result.success(info);
    }

    /**
     * 关注或取关用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @PostMapping("/to/{userId}")
    @Operation(summary = "关注或取关用户", description = "关注或取关用户", method = "POST")
    public Result<Map<String, Object>> followTo(@PathVariable Long userId) {
        Map<String, Object> info = followService.followTo(userId);
        return Result.success(info);
    }
}
