package org.example.springboot.system.mapper;

import org.example.springboot.system.domain.entity.RoleMenuLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色、菜单关系Mapper接口
 * </p>
 */
@Mapper
public interface RoleMenuLinkMapper extends BaseMapper<RoleMenuLink> {

}
