package org.example.springboot.mapper;

import org.example.springboot.domain.entity.ArticleCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章类别Mapper接口
 * </p>
 */
@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {

}
