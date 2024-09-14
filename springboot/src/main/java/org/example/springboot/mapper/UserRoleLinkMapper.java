package org.example.springboot.mapper;

import org.example.springboot.domain.entity.system.UserRoleLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户、角色关系Mapper接口
 * </p>
 */
@Mapper
public interface UserRoleLinkMapper extends BaseMapper<UserRoleLink> {

}
