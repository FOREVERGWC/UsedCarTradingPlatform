package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.ArticleLabelDto;
import org.example.springboot.biz.domain.entity.ArticleLabel;
import org.example.springboot.biz.domain.vo.ArticleLabelVo;

import java.util.List;

/**
 * <p>
 * 文章标签服务类
 * </p>
 */
public interface IArticleLabelService extends IService<ArticleLabel> {
    /**
     * 查询文章标签列表
     *
     * @param dto 文章标签
     * @return 结果
     */
    List<ArticleLabelVo> getList(ArticleLabelDto dto);

    /**
     * 查询文章标签分页
     *
     * @param dto 文章标签
     * @return 结果
     */
    IPage<ArticleLabelVo> getPage(ArticleLabelDto dto);

    /**
     * 查询文章标签
     *
     * @param dto 文章标签
     * @return 结果
     */
    ArticleLabelVo getOne(ArticleLabelDto dto);
}
