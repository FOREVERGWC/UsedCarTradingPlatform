package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.CarAudite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 二手车审核Mapper接口
 * </p>
 */
@Mapper
public interface CarAuditeMapper extends BaseMapper<CarAudite> {

}
