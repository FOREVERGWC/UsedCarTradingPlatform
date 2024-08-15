package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.ArticleLabelLinkDto;
import org.example.springboot.domain.entity.ArticleLabelLink;
import org.example.springboot.domain.vo.ArticleLabelLinkVo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 文章、文章标签关系服务类
 * </p>
 */
public interface IArticleLabelLinkService extends IService<ArticleLabelLink> {
    /**
     * 根据文章ID删除文章、文章标签关系
     *
     * @param articleId 文章ID
     */
    void removeByArticleId(Serializable articleId);

    /**
     * 根据文章ID列表批量删除文章、文章标签关系
     *
     * @param articleIds 文章ID列表
     */
    void removeByArticleIds(Collection<?> articleIds);

    /**
     * 根据标签ID删除文章、文章标签关系
     *
     * @param labelId 标签ID
     */
    void removeByLabelId(Serializable labelId);

    /**
     * 根据标签ID列表批量删除文章、文章标签关系
     *
     * @param labelIds 标签ID列表
     */
    void removeByLabelIds(Collection<?> labelIds);

    /**
     * 查询文章、文章标签关系列表
     *
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    List<ArticleLabelLinkVo> getList(ArticleLabelLinkDto dto);

    /**
     * 查询文章、文章标签关系分页
     *
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    IPage<ArticleLabelLinkVo> getPage(ArticleLabelLinkDto dto);

    /**
     * 查询文章、文章标签关系
     *
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    ArticleLabelLinkVo getOne(ArticleLabelLinkDto dto);
}
