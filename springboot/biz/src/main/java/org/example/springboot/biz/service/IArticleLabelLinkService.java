package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.ArticleLabelLinkDto;
import org.example.springboot.biz.domain.entity.ArticleLabelLink;
import org.example.springboot.biz.domain.vo.ArticleLabelLinkVo;

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
     * 根据作者ID删除文章、文章标签关系
     *
     * @param userId 作者ID
     */
    void removeByUserId(Serializable userId);

    /**
     * 根据作者ID列表批量删除文章、文章标签关系
     *
     * @param userIds 作者ID列表
     */
    void removeByUserIds(Collection<?> userIds);

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
     * 根据作者ID查询文章、文章标签关系列表
     *
     * @param userId 作者ID
     * @return 结果
     */
    List<ArticleLabelLink> listByUserId(Serializable userId);

    /**
     * 根据作者ID列表批量查询文章、文章标签关系列表
     *
     * @param userIds 作者ID列表
     * @return 结果
     */
    List<ArticleLabelLink> listByUserIds(Collection<?> userIds);

    /**
     * 根据文章ID查询标签ID列表
     *
     * @param articleId 文章ID
     * @return 结果
     */
    List<Long> listLabelIdsByArticleId(Long articleId);

    /**
     * 根据文章ID列表批量查询标签ID列表
     *
     * @param articleIds 文章ID列表
     * @return 结果
     */
    List<Long> listLabelIdsByArticleIds(List<Long> articleIds);

    /**
     * 根据文章ID查询作者ID列表
     *
     * @param articleId 文章ID
     * @return 结果
     */
    List<Long> listUserIdsByArticleId(Long articleId);

    /**
     * 根据文章ID列表批量查询作者ID列表
     *
     * @param articleIds 文章ID列表
     * @return 结果
     */
    List<Long> listUserIdsByArticleIds(List<Long> articleIds);

    /**
     * 根据标签ID查询文章ID列表
     *
     * @param labelId 标签ID
     * @return 结果
     */
    List<Long> listArticleIdsByLabelId(Long labelId);

    /**
     * 根据标签ID列表批量查询文章ID列表
     *
     * @param labelIds 标签ID列表
     * @return 结果
     */
    List<Long> listArticleIdsByLabelIds(List<Long> labelIds);

    /**
     * 根据标签ID查询作者ID列表
     *
     * @param labelId 标签ID
     * @return 结果
     */
    List<Long> listUserIdsByLabelId(Long labelId);

    /**
     * 根据标签ID列表批量查询作者ID列表
     *
     * @param labelIds 标签ID列表
     * @return 结果
     */
    List<Long> listUserIdsByLabelIds(List<Long> labelIds);

    /**
     * 根据作者ID查询标签ID列表
     *
     * @param userId 作者ID
     * @return 结果
     */
    List<Long> listLabelIdsByUserId(Long userId);

    /**
     * 根据作者ID列表批量查询标签ID列表
     *
     * @param userIds 作者ID列表
     * @return 结果
     */
    List<Long> listLabelIdsByUserIds(List<Long> userIds);

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
