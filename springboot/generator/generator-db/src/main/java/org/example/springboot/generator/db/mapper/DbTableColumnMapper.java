package org.example.springboot.generator.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springboot.generator.db.domain.entity.DbTableColumn;

/**
 * <p>
 * 数据库列Mapper接口
 * </p>
 */
@Mapper
public interface DbTableColumnMapper extends BaseMapper<DbTableColumn> {

}
