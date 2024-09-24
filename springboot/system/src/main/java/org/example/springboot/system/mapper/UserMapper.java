package org.example.springboot.system.mapper;

import org.example.springboot.system.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息Mapper接口
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}