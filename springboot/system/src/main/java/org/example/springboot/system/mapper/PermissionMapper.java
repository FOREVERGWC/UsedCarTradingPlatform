package org.example.springboot.system.mapper;

import org.example.springboot.system.domain.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限Mapper接口
 * </p>
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
