package org.example.springboot.mapper;

import org.example.springboot.domain.entity.system.LogLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登录日志Mapper接口
 * </p>
 */
@Mapper
public interface LogLoginMapper extends BaseMapper<LogLogin> {

}
