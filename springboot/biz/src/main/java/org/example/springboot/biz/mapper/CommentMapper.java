package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.Comment;
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
