package org.example.springboot.mapper;

import org.example.springboot.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章Mapper接口
 * </p>
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
