package org.example.springboot.generator.gen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.springboot.generator.gen.domain.entity.GenTable;

/**
 * <p>
 * 代码生成业务Mapper接口
 * </p>
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {
    /**
     * 获取当前连接数据库名称
     *
     * @return 结果
     */
    @Select("SELECT DATABASE()")
    String getDbName();
}
