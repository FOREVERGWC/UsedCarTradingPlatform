package org.example.springboot.generator.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springboot.generator.db.domain.entity.DbKeyColumnUsage;

/**
 * <p>
 * 键列使用情况Mapper接口
 * </p>
 */
@Mapper
public interface DbKeyColumnUsageMapper extends BaseMapper<DbKeyColumnUsage> {

}
