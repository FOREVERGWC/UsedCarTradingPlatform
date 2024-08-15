package org.example.springboot.mapper;

import org.example.springboot.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论Mapper接口
 * </p>
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
