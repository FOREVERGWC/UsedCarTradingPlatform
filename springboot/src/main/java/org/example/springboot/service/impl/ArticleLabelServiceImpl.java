package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.domain.dto.ArticleLabelDto;
import org.example.springboot.domain.entity.ArticleLabel;
import org.example.springboot.domain.vo.ArticleLabelVo;
import org.example.springboot.mapper.ArticleLabelMapper;
import org.example.springboot.service.IArticleLabelLinkService;
import org.example.springboot.service.IArticleLabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章标签服务实现类
 * </p>
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements IArticleLabelService {
    @Resource
    private IArticleLabelLinkService articleLabelLinkService;

    @Transactional
    @Override
    public boolean removeById(Serializable id) {
        articleLabelLinkService.removeByLabelId(id);
        return super.removeById(id);
    }

    @Transactional
    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        articleLabelLinkService.removeByLabelIds(list);
        return super.removeBatchByIds(list);
    }

    @Override
    public List<ArticleLabelVo> getList(ArticleLabelDto dto) {
        List<ArticleLabel> articleLabelList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(articleLabelList)) {
            return List.of();
        }
        // 组装VO
        return articleLabelList.stream().map(item -> {
            ArticleLabelVo vo = new ArticleLabelVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public IPage<ArticleLabelVo> getPage(ArticleLabelDto dto) {
        Page<ArticleLabel> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            ArticleLabelVo vo = new ArticleLabelVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public ArticleLabelVo getOne(ArticleLabelDto dto) {
        ArticleLabel one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        ArticleLabelVo vo = new ArticleLabelVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param dto 文章标签
     * @return 结果
     */
    private LambdaQueryChainWrapper<ArticleLabel> getWrapper(ArticleLabelDto dto) {
        Map<String, Object> params = dto.getParams();
        // 创建时间
        Object startCreateTime = params == null ? null : params.get("startCreateTime");
        Object endCreateTime = params == null ? null : params.get("endCreateTime");
        return lambdaQuery()
                .eq(dto.getId() != null, ArticleLabel::getId, dto.getId())
                .like(StrUtil.isNotBlank(dto.getName()), ArticleLabel::getName, dto.getName())
                .between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime), ArticleLabel::getCreateTime, startCreateTime, endCreateTime);
    }
}
