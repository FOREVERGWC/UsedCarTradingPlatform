package org.example.springboot.mapper;

import org.example.springboot.domain.entity.system.RoleMenuLink;
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
