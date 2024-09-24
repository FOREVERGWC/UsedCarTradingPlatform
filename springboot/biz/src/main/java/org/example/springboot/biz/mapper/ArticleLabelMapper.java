package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.ArticleLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章标签Mapper接口
 * </p>
 */
@Mapper
public interface ArticleLabelMapper extends BaseMapper<ArticleLabel> {

}
