package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.domain.dto.ArticleCategoryDto;
import org.example.springboot.domain.entity.ArticleCategory;
import org.example.springboot.domain.vo.ArticleCategoryVo;
import org.example.springboot.mapper.ArticleCategoryMapper;
import org.example.springboot.service.IArticleCategoryService;
import org.example.springboot.utils.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章类别服务实现类
 * </p>
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {
    @Override
    public boolean saveOrUpdate(ArticleCategory entity) {
        if (entity.getId() == null) {
            // TODO 删除枚举
            entity.setDeleted(0);
            return super.save(entity);
        }
        return super.saveOrUpdate(entity);
    }

    @Override
    public List<ArticleCategoryVo> getList(ArticleCategoryDto dto) {
        List<ArticleCategory> articleCategoryList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(articleCategoryList)) {
            return List.of();
        }
        // 组装VO
        return articleCategoryList.stream().map(item -> {
            ArticleCategoryVo vo = new ArticleCategoryVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public List<ArticleCategoryVo> getTree(ArticleCategoryDto dto) {
        List<ArticleCategory> articleCategoryList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(articleCategoryList)) {
            return List.of();
        }
        // 组装VO
        List<ArticleCategoryVo> vos = articleCategoryList.stream().map(item -> {
            ArticleCategoryVo vo = new ArticleCategoryVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
        // 树
        return DataUtils.listToTree(vos, ArticleCategoryVo::getParentId, ArticleCategoryVo::setChildren, ArticleCategoryVo::getId, 0L);
    }

    @Override
    public IPage<ArticleCategoryVo> getPage(ArticleCategoryDto dto) {
        Page<ArticleCategory> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            ArticleCategoryVo vo = new ArticleCategoryVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public ArticleCategoryVo getOne(ArticleCategoryDto dto) {
        ArticleCategory one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        ArticleCategoryVo vo = new ArticleCategoryVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param dto 文章类别
     * @return 结果
     */
    private LambdaQueryChainWrapper<ArticleCategory> getWrapper(ArticleCategoryDto dto) {
        Map<String, Object> params = dto.getParams();
        // 创建时间
        Object startCreateTime = params == null ? null : params.get("startCreateTime");
        Object endCreateTime = params == null ? null : params.get("endCreateTime");
        return lambdaQuery()
                .eq(dto.getId() != null, ArticleCategory::getId, dto.getId())
                .like(StrUtil.isNotBlank(dto.getName()), ArticleCategory::getName, dto.getName())
                .eq(dto.getParentId() != null, ArticleCategory::getParentId, dto.getParentId())
                .eq(dto.getDeleted() != null, ArticleCategory::getDeleted, dto.getDeleted())
                .between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime), ArticleCategory::getCreateTime, startCreateTime, endCreateTime);
    }
}
