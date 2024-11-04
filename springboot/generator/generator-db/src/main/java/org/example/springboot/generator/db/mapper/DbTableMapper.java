package org.example.springboot.generator.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springboot.generator.db.domain.entity.DbTable;

/**
 * <p>
 * 数据库表Mapper接口
 * </p>
 */
@Mapper
public interface DbTableMapper extends BaseMapper<DbTable> {

}
