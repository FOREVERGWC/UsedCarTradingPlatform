package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.ArticleDto;
import org.example.springboot.domain.entity.Article;
import org.example.springboot.domain.vo.ArticleVo;

import java.util.List;

/**
 * <p>
 * 文章服务类
 * </p>
 */
public interface IArticleService extends IService<Article> {
    /**
     * 查询文章列表
     *
     * @param dto 文章
     * @return 结果
     */
    List<ArticleVo> getList(ArticleDto dto);

    /**
     * 查询文章分页
     *
     * @param dto 文章
     * @return 结果
     */
    IPage<ArticleVo> getPage(ArticleDto dto);

    /**
     * 查询文章
     *
     * @param dto 文章
     * @return 结果
     */
    ArticleVo getOne(ArticleDto dto);

    /**
     * 置顶或取消置顶文章
     *
     * @param id 文章ID
     */
    void handleTop(Long id);

    /**
     * 允许或禁止文章评论
     *
     * @param id 文章ID
     */
    void handleComment(Long id);
}
