package org.example.springboot.system.mapper;

import org.example.springboot.system.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色Mapper接口
 * </p>
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
