package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.common.BaseContext;
import org.example.springboot.common.enums.ArticleStatus;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.dto.ArticleDto;
import org.example.springboot.domain.entity.*;
import org.example.springboot.domain.vo.ArticleVo;
import org.example.springboot.domain.vo.UserVo;
import org.example.springboot.mapper.ArticleMapper;
import org.example.springboot.service.*;
import org.example.springboot.service.impl.cache.IArticleCacheService;
import org.example.springboot.utils.LabelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章服务实现类
 * </p>
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Resource
    private IArticleCategoryService articleCategoryService;
    @Resource
    private IUserService userService;
    @Resource
    private IArticleLabelService articleLabelService;
    @Resource
    private IArticleLabelLinkService articleLabelLinkService;
    @Resource
    private IArticleCacheService articleCacheService;

    @Transactional
    @Override
    public boolean saveOrUpdate(Article entity) {
        if (entity.getId() == null) {
            UserVo user = BaseContext.getUser();
            entity.setUserId(user.getId());
            entity.setViewCount(0L);
            entity.setLikeCount(0L);
            entity.setDislikeCount(0L);
            entity.setCommentCount(0L);
            entity.setCollectionCount(0L);
            // TODO 状态暂时默认为已发布
            entity.setStatus(ArticleStatus.PUBLISHED.getCode());
            // TODO 后期添加定时发布功能，暂时为创建即发布
            entity.setReleaseTime(LocalDateTime.now());
            boolean flag = super.save(entity);
            handleArticleLabelList(entity);
            return flag;
        }
        boolean flag = super.saveOrUpdate(entity);
        handleArticleLabelList(entity);
        return flag;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable id) {
        articleLabelLinkService.removeByArticleId(id);
        return super.removeById(id);
    }

    @Transactional
    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        articleLabelLinkService.removeByArticleIds(list);
        return super.removeBatchByIds(list);
    }

    @Override
    public List<ArticleVo> getList(ArticleDto dto) {
        List<Article> articleList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(articleList)) {
            return List.of();
        }
        // 类别
        List<Long> categoryIdList = articleList.stream().map(Article::getCategoryId).toList();
        List<ArticleCategory> categoryList = articleCategoryService.listByIds(categoryIdList);
        Map<Long, ArticleCategory> categoryMap = categoryList.stream().collect(Collectors.toMap(ArticleCategory::getId, item -> item));
        // 作者
        List<Long> userIdList = articleList.stream().map(Article::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return articleList.stream().map(item -> {
            ArticleVo vo = new ArticleVo();
            BeanUtils.copyProperties(item, vo);
            vo.setViewCount(articleCacheService.getViewCount(item.getId()));
            vo.setCategory(categoryMap.getOrDefault(item.getCategoryId(), ArticleCategory.builder().name("已删除").build()));
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<ArticleVo> getPage(ArticleDto dto) {
        Page<Article> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 类别
        List<Long> categoryIdList = info.getRecords().stream().map(Article::getCategoryId).toList();
        List<ArticleCategory> categoryList = articleCategoryService.listByIds(categoryIdList);
        Map<Long, ArticleCategory> categoryMap = categoryList.stream().collect(Collectors.toMap(ArticleCategory::getId, item -> item));
        // 作者
        List<Long> userIdList = info.getRecords().stream().map(Article::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            ArticleVo vo = new ArticleVo();
            BeanUtils.copyProperties(item, vo);
            vo.setViewCount(articleCacheService.getViewCount(item.getId()));
            vo.setCategory(categoryMap.getOrDefault(item.getCategoryId(), ArticleCategory.builder().name("已删除").build()));
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public ArticleVo getOne(ArticleDto dto) {
        Article one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 类别
        ArticleCategory category = Optional.ofNullable(articleCategoryService.getById(one.getCategoryId())).orElse(ArticleCategory.builder().name("已删除").build());
        // 作者
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        ArticleVo vo = new ArticleVo();
        BeanUtils.copyProperties(one, vo);
        vo.setViewCount(articleCacheService.getViewCount(one.getId()));
        vo.setCategory(category);
        vo.setUser(user);
        return vo;
    }

    @Override
    public void handleTop(Long id) {
        Article article = getById(id);
        if (article == null) {
            throw new CustomException(ResultCode.ARTICLE_NOT_FOUND_ERROR);
        }
        article.setTop(!article.getTop());
        updateById(article);
    }

    @Override
    public void handleComment(Long id) {
        Article article = getById(id);
        if (article == null) {
            throw new CustomException(ResultCode.ARTICLE_NOT_FOUND_ERROR);
        }
        article.setCommentable(!article.getCommentable());
        updateById(article);
    }

    /**
     * 处理文章标签
     *
     * @param article 文章
     */
    private void handleArticleLabelList(Article article) {
        List<String> labelList = LabelUtils.generateLabelList(article.getContent());
        if (CollectionUtil.isEmpty(labelList)) {
            return;
        }
        // 移除文章、文章标签关系
        articleLabelLinkService.lambdaUpdate()
                .eq(ArticleLabelLink::getArticleId, article.getId())
                .remove();
        // 获取存在的标签
        List<ArticleLabel> existingLabelList = articleLabelService.lambdaQuery()
                .in(ArticleLabel::getName, labelList)
                .list();
        Map<String, Long> existingLabelMap = existingLabelList.stream().collect(Collectors.toMap(ArticleLabel::getName, ArticleLabel::getId));
        List<ArticleLabel> newLabels = new ArrayList<>();
        List<ArticleLabelLink> labelLinks = new ArrayList<>();
        for (String label : labelList) {
            Long labelId = existingLabelMap.get(label);
            if (labelId == null) {
                ArticleLabel one = ArticleLabel.builder().name(label).build();
                newLabels.add(one);
            } else {
                ArticleLabelLink link = ArticleLabelLink.builder()
                        .articleId(article.getId())
                        .labelId(labelId)
                        .build();
                labelLinks.add(link);
            }
        }
        if (CollectionUtil.isNotEmpty(newLabels)) {
            articleLabelService.saveBatch(newLabels);
            newLabels.forEach(newLabel -> labelLinks.add(ArticleLabelLink.builder()
                    .articleId(article.getId())
                    .labelId(newLabel.getId())
                    .build()));
        }
        if (CollectionUtil.isNotEmpty(labelLinks)) {
            articleLabelLinkService.saveBatch(labelLinks);
        }
    }

    /**
     * 组装查询包装器
     *
     * @param dto 文章
     * @return 结果
     */
    private LambdaQueryChainWrapper<Article> getWrapper(ArticleDto dto) {
        Map<String, Object> params = dto.getParams();
        // 创建时间
        Object startCreateTime = params == null ? null : params.get("startCreateTime");
        Object endCreateTime = params == null ? null : params.get("endCreateTime");
        return lambdaQuery()
                .eq(dto.getId() != null, Article::getId, dto.getId())
                .like(StrUtil.isNotBlank(dto.getTitle()), Article::getTitle, dto.getTitle())
                .eq(dto.getCategoryId() != null, Article::getCategoryId, dto.getCategoryId())
                .like(StrUtil.isNotBlank(dto.getContent()), Article::getContent, dto.getContent())
                .eq(dto.getUserId() != null, Article::getUserId, dto.getUserId())
                .eq(dto.getViewCount() != null, Article::getViewCount, dto.getViewCount())
                .eq(dto.getLikeCount() != null, Article::getLikeCount, dto.getLikeCount())
                .eq(dto.getDislikeCount() != null, Article::getDislikeCount, dto.getDislikeCount())
                .eq(dto.getCommentCount() != null, Article::getCommentCount, dto.getCommentCount())
                .eq(dto.getCollectionCount() != null, Article::getCollectionCount, dto.getCollectionCount())
                .eq(dto.getTop() != null, Article::getTop, dto.getTop())
                .like(StrUtil.isNotBlank(dto.getVisible()), Article::getVisible, dto.getVisible())
                .eq(dto.getCommentable() != null, Article::getCommentable, dto.getCommentable())
                .like(StrUtil.isNotBlank(dto.getStatus()), Article::getStatus, dto.getStatus())
                .between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime), Article::getCreateTime, startCreateTime, endCreateTime)
                .orderByDesc(Article::getTop);
    }
}
