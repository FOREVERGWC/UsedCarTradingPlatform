package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 关注Mapper接口
 * </p>
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

}