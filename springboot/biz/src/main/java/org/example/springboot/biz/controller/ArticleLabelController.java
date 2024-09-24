package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.system.domain.Result;
import org.example.springboot.biz.domain.dto.ArticleLabelDto;
import org.example.springboot.biz.domain.entity.ArticleLabel;
import org.example.springboot.biz.domain.vo.ArticleLabelVo;
import org.example.springboot.biz.service.IArticleLabelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章标签前端控制器
 * </p>
 */
@RestController
@RequestMapping("/articleLabel")
@Tag(name = "文章标签", description = "文章标签")
public class ArticleLabelController {
    @Resource
    private IArticleLabelService articleLabelService;

    /**
     * 添加、修改文章标签
     *
     * @param articleLabel 文章标签
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改文章标签", description = "添加、修改文章标签", method = "POST")
    public Result<Void> save(@RequestBody ArticleLabel articleLabel) {
        articleLabelService.saveOrUpdate(articleLabel);
        return Result.success();
    }

    /**
     * 删除文章标签
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除文章标签", description = "删除文章标签", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        articleLabelService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询文章标签列表
     *
     * @param dto 文章标签
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章标签列表", description = "查询文章标签列表", method = "GET")
    public Result<List<ArticleLabelVo>> getList(ArticleLabelDto dto) {
        List<ArticleLabelVo> list = articleLabelService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询文章标签分页
     *
     * @param dto 文章标签
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询文章标签分页", description = "查询文章标签分页", method = "GET")
    public Result<IPage<ArticleLabelVo>> getPage(ArticleLabelDto dto) {
        IPage<ArticleLabelVo> page = articleLabelService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询文章标签
     *
     * @param dto 文章标签
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询文章标签", description = "查询文章标签", method = "GET")
    public Result<ArticleLabelVo> getOne(ArticleLabelDto dto) {
        ArticleLabelVo vo = articleLabelService.getOne(dto);
        return Result.success(vo);
    }
}
