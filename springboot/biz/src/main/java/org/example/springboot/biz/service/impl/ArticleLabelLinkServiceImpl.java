package org.example.springboot.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.ArticleLabelLinkDto;
import org.example.springboot.biz.domain.entity.Article;
import org.example.springboot.biz.domain.entity.ArticleLabel;
import org.example.springboot.biz.domain.entity.ArticleLabelLink;
import org.example.springboot.biz.domain.vo.ArticleLabelLinkVo;
import org.example.springboot.biz.mapper.ArticleLabelLinkMapper;
import org.example.springboot.biz.mapper.ArticleLabelMapper;
import org.example.springboot.biz.mapper.ArticleMapper;
import org.example.springboot.biz.service.IArticleLabelLinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章、文章标签关系服务实现类
 * </p>
 */
@Service
public class ArticleLabelLinkServiceImpl extends ServiceImpl<ArticleLabelLinkMapper, ArticleLabelLink> implements IArticleLabelLinkService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleLabelMapper articleLabelMapper;

    @Override
    public void removeByArticleId(Serializable articleId) {
        lambdaUpdate()
                .eq(ArticleLabelLink::getArticleId, articleId)
                .remove();
    }

    @Override
    public void removeByArticleIds(Collection<?> articleIds) {
        lambdaUpdate()
                .in(ArticleLabelLink::getArticleId, articleIds)
                .remove();
    }

    @Override
    public void removeByLabelId(Serializable labelId) {
        lambdaUpdate()
                .eq(ArticleLabelLink::getLabelId, labelId)
                .remove();
    }

    @Override
    public void removeByLabelIds(Collection<?> labelIds) {
        lambdaUpdate()
                .in(ArticleLabelLink::getLabelId, labelIds)
                .remove();
    }

    @Override
    public void removeByUserId(Serializable userId) {
        lambdaUpdate()
                .eq(ArticleLabelLink::getUserId, userId)
                .remove();
    }

    @Override
    public void removeByUserIds(Collection<?> userIds) {
        lambdaUpdate()
                .in(ArticleLabelLink::getUserId, userIds)
                .remove();
    }

    @Override
    public List<ArticleLabelLink> listByArticleId(Serializable articleId) {
        return Optional.ofNullable(lambdaQuery()
                        .eq(ArticleLabelLink::getArticleId, articleId)
                        .list())
                .orElse(List.of());
    }

    @Override
    public List<ArticleLabelLink> listByArticleIds(Collection<?> articleIds) {
        return Optional.ofNullable(lambdaQuery()
                        .in(ArticleLabelLink::getArticleId, articleIds)
                        .list())
                .orElse(List.of());
    }

    @Override
    public List<ArticleLabelLink> listByLabelId(Serializable labelId) {
        return Optional.ofNullable(lambdaQuery()
                        .eq(ArticleLabelLink::getLabelId, labelId)
                        .list())
                .orElse(List.of());
    }

    @Override
    public List<ArticleLabelLink> listByLabelIds(Collection<?> labelIds) {
        return Optional.ofNullable(lambdaQuery()
                        .in(ArticleLabelLink::getLabelId, labelIds)
                        .list())
                .orElse(List.of());
    }

    @Override
    public List<ArticleLabelLink> listByUserId(Serializable userId) {
        return Optional.ofNullable(lambdaQuery()
                        .eq(ArticleLabelLink::getUserId, userId)
                        .list())
                .orElse(List.of());
    }

    @Override
    public List<ArticleLabelLink> listByUserIds(Collection<?> userIds) {
        return Optional.ofNullable(lambdaQuery()
                        .in(ArticleLabelLink::getUserId, userIds)
                        .list())
                .orElse(List.of());
    }

    @Override
    public List<Long> listLabelIdsByArticleId(Long articleId) {
        List<ArticleLabelLink> linkList = listByArticleId(articleId);
        return linkList.stream().map(ArticleLabelLink::getLabelId).distinct().toList();
    }

    @Override
    public List<Long> listLabelIdsByArticleIds(List<Long> articleIds) {
        if (CollectionUtil.isEmpty(articleIds)) {
            return List.of();
        }
        List<ArticleLabelLink> linkList = listByArticleIds(articleIds);
        return linkList.stream().map(ArticleLabelLink::getLabelId).distinct().toList();
    }

    @Override
    public List<Long> listUserIdsByArticleId(Long articleId) {
        List<ArticleLabelLink> linkList = listByArticleId(articleId);
        return linkList.stream().map(ArticleLabelLink::getUserId).distinct().toList();
    }

    @Override
    public List<Long> listUserIdsByArticleIds(List<Long> articleIds) {
        if (CollectionUtil.isEmpty(articleIds)) {
            return List.of();
        }
        List<ArticleLabelLink> linkList = listByArticleIds(articleIds);
        return linkList.stream().map(ArticleLabelLink::getUserId).distinct().toList();
    }

    @Override
    public List<Long> listArticleIdsByLabelId(Long labelId) {
        List<ArticleLabelLink> linkList = listByLabelId(labelId);
        return linkList.stream().map(ArticleLabelLink::getArticleId).distinct().toList();
    }

    @Override
    public List<Long> listArticleIdsByLabelIds(List<Long> labelIds) {
        if (CollectionUtil.isEmpty(labelIds)) {
            return List.of();
        }
        List<ArticleLabelLink> linkList = listByLabelIds(labelIds);
        return linkList.stream().map(ArticleLabelLink::getArticleId).distinct().toList();
    }

    @Override
    public List<Long> listUserIdsByLabelId(Long labelId) {
        List<ArticleLabelLink> linkList = listByLabelId(labelId);
        return linkList.stream().map(ArticleLabelLink::getUserId).distinct().toList();
    }

    @Override
    public List<Long> listUserIdsByLabelIds(List<Long> labelIds) {
        if (CollectionUtil.isEmpty(labelIds)) {
            return List.of();
        }
        List<ArticleLabelLink> linkList = listByLabelIds(labelIds);
        return linkList.stream().map(ArticleLabelLink::getUserId).distinct().toList();
    }

    @Override
    public List<Long> listLabelIdsByUserId(Long userId) {
        List<ArticleLabelLink> linkList = listByUserId(userId);
        return linkList.stream().map(ArticleLabelLink::getLabelId).distinct().toList();
    }

    @Override
    public List<Long> listLabelIdsByUserIds(List<Long> userIds) {
        if (CollectionUtil.isEmpty(userIds)) {
            return List.of();
        }
        List<ArticleLabelLink> linkList = listByUserIds(userIds);
        return linkList.stream().map(ArticleLabelLink::getLabelId).distinct().toList();
    }

    @Override
    public List<ArticleLabelLinkVo> getList(ArticleLabelLinkDto dto) {
        List<ArticleLabelLink> articleLabelLinkList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(articleLabelLinkList)) {
            return List.of();
        }
        // 文章
        List<Long> articleIdList = articleLabelLinkList.stream().map(ArticleLabelLink::getArticleId).toList();
        List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<Article>().in(Article::getId, articleIdList));
        Map<Long, Article> articleMap = articleList.stream().collect(Collectors.toMap(Article::getId, item -> item));
        // 标签
        List<Long> labelIdList = articleLabelLinkList.stream().map(ArticleLabelLink::getLabelId).toList();
        List<ArticleLabel> labelList = articleLabelMapper.selectList(new LambdaQueryWrapper<ArticleLabel>().in(ArticleLabel::getId, labelIdList));
        Map<Long, ArticleLabel> labelMap = labelList.stream().collect(Collectors.toMap(ArticleLabel::getId, item -> item));
        // 组装VO
        return articleLabelLinkList.stream().map(item -> {
            ArticleLabelLinkVo vo = new ArticleLabelLinkVo();
            BeanUtils.copyProperties(item, vo);
            vo.setArticle(articleMap.getOrDefault(item.getArticleId(), Article.builder().title("已删除").build()));
            vo.setLabel(labelMap.getOrDefault(item.getLabelId(), ArticleLabel.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<ArticleLabelLinkVo> getPage(ArticleLabelLinkDto dto) {
        Page<ArticleLabelLink> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 文章
        List<Long> articleIdList = info.getRecords().stream().map(ArticleLabelLink::getArticleId).toList();
        List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<Article>().in(Article::getId, articleIdList));
        Map<Long, Article> articleMap = articleList.stream().collect(Collectors.toMap(Article::getId, item -> item));
        // 标签
        List<Long> labelIdList = info.getRecords().stream().map(ArticleLabelLink::getLabelId).toList();
        List<ArticleLabel> labelList = articleLabelMapper.selectList(new LambdaQueryWrapper<ArticleLabel>().in(ArticleLabel::getId, labelIdList));
        Map<Long, ArticleLabel> labelMap = labelList.stream().collect(Collectors.toMap(ArticleLabel::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            ArticleLabelLinkVo vo = new ArticleLabelLinkVo();
            BeanUtils.copyProperties(item, vo);
            vo.setArticle(articleMap.getOrDefault(item.getArticleId(), Article.builder().title("已删除").build()));
            vo.setLabel(labelMap.getOrDefault(item.getLabelId(), ArticleLabel.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public ArticleLabelLinkVo getOne(ArticleLabelLinkDto dto) {
        ArticleLabelLink one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 文章
        Article article = Optional.ofNullable(articleMapper.selectById(one.getArticleId())).orElse(Article.builder().title("已删除").build());
        // 标签
        ArticleLabel label = Optional.ofNullable(articleLabelMapper.selectById(one.getLabelId())).orElse(ArticleLabel.builder().name("已删除").build());
        // 组装VO
        ArticleLabelLinkVo vo = new ArticleLabelLinkVo();
        BeanUtils.copyProperties(one, vo);
        vo.setArticle(article);
        vo.setLabel(label);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 文章、文章标签关系
     * @return 结果
     */
    private LambdaQueryChainWrapper<ArticleLabelLink> getWrapper(ArticleLabelLink entity) {
        LambdaQueryChainWrapper<ArticleLabelLink> wrapper = lambdaQuery()
                .eq(entity.getId() != null, ArticleLabelLink::getId, entity.getId())
                .eq(entity.getArticleId() != null, ArticleLabelLink::getArticleId, entity.getArticleId())
                .eq(entity.getLabelId() != null, ArticleLabelLink::getLabelId, entity.getLabelId())
                .eq(entity.getUserId() != null, ArticleLabelLink::getUserId, entity.getUserId());
        if (entity instanceof ArticleLabelLinkDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    ArticleLabelLink::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
