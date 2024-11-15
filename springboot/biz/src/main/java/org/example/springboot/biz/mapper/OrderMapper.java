package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单Mapper接口
 * </p>
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
