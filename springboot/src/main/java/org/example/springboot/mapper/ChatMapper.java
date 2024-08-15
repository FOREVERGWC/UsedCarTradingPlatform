package org.example.springboot.mapper;

import org.example.springboot.domain.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天Mapper接口
 * </p>
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}
