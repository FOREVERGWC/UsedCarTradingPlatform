package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.ArticleLabelLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章、文章标签关系Mapper接口
 * </p>
 */
@Mapper
public interface ArticleLabelLinkMapper extends BaseMapper<ArticleLabelLink> {

}
