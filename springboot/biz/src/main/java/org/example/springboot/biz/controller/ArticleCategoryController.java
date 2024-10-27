package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.common.domain.Result;
import org.example.springboot.biz.domain.dto.ArticleCategoryDto;
import org.example.springboot.biz.domain.entity.ArticleCategory;
import org.example.springboot.biz.domain.vo.ArticleCategoryVo;
import org.example.springboot.biz.service.IArticleCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章类别前端控制器
 * </p>
 */
@RestController
@RequestMapping("/articleCategory")
@Tag(name = "文章类别", description = "文章类别")
public class ArticleCategoryController {
    @Resource
    private IArticleCategoryService articleCategoryService;

    /**
     * 添加、修改文章类别
     *
     * @param articleCategory 文章类别
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改文章类别", description = "添加、修改文章类别", method = "POST")
    public Result<Void> save(@RequestBody ArticleCategory articleCategory) {
        articleCategoryService.saveOrUpdate(articleCategory);
        return Result.success();
    }

    /**
     * 删除文章类别
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除文章类别", description = "删除文章类别", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        articleCategoryService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询文章类别列表
     *
     * @param dto 文章类别
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章类别列表", description = "查询文章类别列表", method = "GET")
    public Result<List<ArticleCategoryVo>> getList(ArticleCategoryDto dto) {
        List<ArticleCategoryVo> list = articleCategoryService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询文章类别树
     *
     * @param dto 文章类别
     * @return 结果
     */
    @GetMapping("/tree")
    @Operation(summary = "查询文章类别树", description = "查询文章类别树", method = "GET")
    public Result<List<ArticleCategoryVo>> getTree(ArticleCategoryDto dto) {
        List<ArticleCategoryVo> list = articleCategoryService.getTree(dto);
        return Result.success(list);
    }

    /**
     * 查询文章类别分页
     *
     * @param dto 文章类别
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询文章类别分页", description = "查询文章类别分页", method = "GET")
    public Result<IPage<ArticleCategoryVo>> getPage(ArticleCategoryDto dto) {
        IPage<ArticleCategoryVo> page = articleCategoryService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询文章类别
     *
     * @param dto 文章类别
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询文章类别", description = "查询文章类别", method = "GET")
    public Result<ArticleCategoryVo> getOne(ArticleCategoryDto dto) {
        ArticleCategoryVo vo = articleCategoryService.getOne(dto);
        return Result.success(vo);
    }
}
