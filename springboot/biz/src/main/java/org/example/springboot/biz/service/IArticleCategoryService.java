package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.ArticleCategoryDto;
import org.example.springboot.biz.domain.entity.ArticleCategory;
import org.example.springboot.biz.domain.vo.ArticleCategoryVo;

import java.util.List;

/**
 * <p>
 * 文章类别服务类
 * </p>
 */
public interface IArticleCategoryService extends IService<ArticleCategory> {
    /**
     * 查询文章类别列表
     *
     * @param dto 文章类别
     * @return 结果
     */
    List<ArticleCategoryVo> getList(ArticleCategoryDto dto);

    /**
     * 查询文章类别树
     *
     * @param dto 文章类别
     * @return 结果
     */
    List<ArticleCategoryVo> getTree(ArticleCategoryDto dto);

    /**
     * 查询文章类别分页
     *
     * @param dto 文章类别
     * @return 结果
     */
    IPage<ArticleCategoryVo> getPage(ArticleCategoryDto dto);

    /**
     * 查询文章类别
     *
     * @param dto 文章类别
     * @return 结果
     */
    ArticleCategoryVo getOne(ArticleCategoryDto dto);
}
