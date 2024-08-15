package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.domain.dto.ArticleLabelLinkDto;
import org.example.springboot.domain.entity.Article;
import org.example.springboot.domain.entity.ArticleLabel;
import org.example.springboot.domain.entity.ArticleLabelLink;
import org.example.springboot.domain.vo.ArticleLabelLinkVo;
import org.example.springboot.mapper.ArticleLabelLinkMapper;
import org.example.springboot.mapper.ArticleLabelMapper;
import org.example.springboot.mapper.ArticleMapper;
import org.example.springboot.service.IArticleLabelLinkService;
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
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    private LambdaQueryChainWrapper<ArticleLabelLink> getWrapper(ArticleLabelLinkDto dto) {
        Map<String, Object> params = dto.getParams();
        // 创建时间
        Object startCreateTime = params == null ? null : params.get("startCreateTime");
        Object endCreateTime = params == null ? null : params.get("endCreateTime");
        return lambdaQuery()
                .eq(dto.getId() != null, ArticleLabelLink::getId, dto.getId())
                .eq(dto.getArticleId() != null, ArticleLabelLink::getArticleId, dto.getArticleId())
                .eq(dto.getLabelId() != null, ArticleLabelLink::getLabelId, dto.getLabelId())
                .between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime), ArticleLabelLink::getCreateTime, startCreateTime, endCreateTime);
    }
}
