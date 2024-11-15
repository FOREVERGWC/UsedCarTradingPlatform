package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 二手车Mapper接口
 * </p>
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
