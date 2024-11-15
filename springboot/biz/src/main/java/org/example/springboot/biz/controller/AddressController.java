package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.AddressDto;
import org.example.springboot.biz.domain.entity.Address;
import org.example.springboot.biz.domain.vo.AddressVo;
import org.example.springboot.biz.service.IAddressService;
import org.example.springboot.common.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户地址前端控制器
 * </p>
 */
@RestController
@RequestMapping("/address")
@Tag(name = "用户地址", description = "用户地址")
public class AddressController {
    @Resource
    private IAddressService addressService;

    /**
     * 添加、修改用户地址
     *
     * @param address 用户地址
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改用户地址", description = "添加、修改用户地址", method = "POST")
    public Result<Void> save(@RequestBody Address address) {
        addressService.saveOrUpdate(address);
        return Result.success();
    }

    /**
     * 删除用户地址
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除用户地址", description = "删除用户地址", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        addressService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询用户地址列表
     *
     * @param dto 用户地址
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询用户地址列表", description = "查询用户地址列表", method = "GET")
    public Result<List<AddressVo>> getList(AddressDto dto) {
        List<AddressVo> list = addressService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询用户地址分页
     *
     * @param dto 用户地址
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询用户地址分页", description = "查询用户地址分页", method = "GET")
    public Result<IPage<AddressVo>> getPage(AddressDto dto) {
        IPage<AddressVo> page = addressService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询用户地址
     *
     * @param dto 用户地址
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询用户地址", description = "查询用户地址", method = "GET")
    public Result<AddressVo> getOne(AddressDto dto) {
        AddressVo vo = addressService.getOne(dto);
        return Result.success(vo);
    }
}
