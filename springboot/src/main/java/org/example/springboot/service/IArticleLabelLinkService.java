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
     * 根据文章ID查询文章、文章标签关系列表
     *
     * @param articleId 文章ID
     * @return 结果
     */
    List<ArticleLabelLink> listByArticleId(Serializable articleId);

    /**
     * 根据文章ID列表批量查询文章、文章标签关系列表
     *
     * @param articleIds 文章ID列表
     * @return 结果
     */
    List<ArticleLabelLink> listByArticleIds(Collection<?> articleIds);

    /**
     * 根据标签ID查询文章、文章标签关系列表
     *
     * @param labelId 标签ID
     * @return 结果
     */
    List<ArticleLabelLink> listByLabelId(Serializable labelId);

    /**
     * 根据标签ID列表批量查询文章、文章标签关系列表
     *
     * @param labelIds 标签ID列表
     * @return 结果
     */
    List<ArticleLabelLink> listByLabelIds(Collection<?> labelIds);

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
