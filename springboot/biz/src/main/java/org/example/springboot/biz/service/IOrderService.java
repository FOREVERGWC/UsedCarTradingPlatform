package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.OrderDto;
import org.example.springboot.biz.domain.entity.Order;
import org.example.springboot.biz.domain.vo.OrderVo;

import java.util.List;

/**
 * <p>
 * 订单服务类
 * </p>
 */
public interface IOrderService extends IService<Order> {
    /**
     * 查询订单列表
     *
     * @param dto 订单
     * @return 结果
     */
    List<OrderVo> getList(OrderDto dto);

    /**
     * 查询订单分页
     *
     * @param dto 订单
     * @return 结果
     */
    IPage<OrderVo> getPage(OrderDto dto);

    /**
     * 查询订单
     *
     * @param dto 订单
     * @return 结果
     */
    OrderVo getOne(OrderDto dto);
}
