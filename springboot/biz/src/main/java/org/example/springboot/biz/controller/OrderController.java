package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.OrderDto;
import org.example.springboot.biz.domain.entity.Order;
import org.example.springboot.biz.domain.vo.OrderVo;
import org.example.springboot.biz.service.IOrderService;
import org.example.springboot.common.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单前端控制器
 * </p>
 */
@RestController
@RequestMapping("/order")
@Tag(name = "订单", description = "订单")
public class OrderController {
    @Resource
    private IOrderService orderService;

    /**
     * 添加、修改订单
     *
     * @param order 订单
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改订单", description = "添加、修改订单", method = "POST")
    public Result<Void> save(@RequestBody Order order) {
        orderService.saveOrUpdate(order);
        return Result.success();
    }

    /**
     * 删除订单
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除订单", description = "删除订单", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        orderService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询订单列表
     *
     * @param dto 订单
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询订单列表", description = "查询订单列表", method = "GET")
    public Result<List<OrderVo>> getList(OrderDto dto) {
        List<OrderVo> list = orderService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询订单分页
     *
     * @param dto 订单
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询订单分页", description = "查询订单分页", method = "GET")
    public Result<IPage<OrderVo>> getPage(OrderDto dto) {
        IPage<OrderVo> page = orderService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询订单
     *
     * @param dto 订单
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询订单", description = "查询订单", method = "GET")
    public Result<OrderVo> getOne(OrderDto dto) {
        OrderVo vo = orderService.getOne(dto);
        return Result.success(vo);
    }
}
