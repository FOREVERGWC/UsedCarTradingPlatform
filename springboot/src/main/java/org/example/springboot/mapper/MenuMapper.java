package org.example.springboot.mapper;

import org.example.springboot.domain.entity.system.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单Mapper接口
 * </p>
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
