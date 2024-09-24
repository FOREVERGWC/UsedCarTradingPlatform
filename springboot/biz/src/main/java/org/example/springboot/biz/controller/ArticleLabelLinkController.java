package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.system.domain.Result;
import org.example.springboot.biz.domain.dto.ArticleLabelLinkDto;
import org.example.springboot.biz.domain.entity.ArticleLabelLink;
import org.example.springboot.biz.domain.vo.ArticleLabelLinkVo;
import org.example.springboot.biz.service.IArticleLabelLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章、文章标签关系前端控制器
 * </p>
 */
@RestController
@RequestMapping("/articleLabelLink")
@Tag(name = "文章、文章标签关系", description = "文章、文章标签关系")
public class ArticleLabelLinkController {
    @Resource
    private IArticleLabelLinkService articleLabelLinkService;

    /**
     * 添加、修改文章、文章标签关系
     *
     * @param articleLabelLink 文章、文章标签关系
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改文章、文章标签关系", description = "添加、修改文章、文章标签关系", method = "POST")
    public Result<Void> save(@RequestBody ArticleLabelLink articleLabelLink) {
        articleLabelLinkService.saveOrUpdate(articleLabelLink);
        return Result.success();
    }

    /**
     * 删除文章、文章标签关系
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除文章、文章标签关系", description = "删除文章、文章标签关系", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        articleLabelLinkService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询文章、文章标签关系列表
     *
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章、文章标签关系列表", description = "查询文章、文章标签关系列表", method = "GET")
    public Result<List<ArticleLabelLinkVo>> getList(ArticleLabelLinkDto dto) {
        List<ArticleLabelLinkVo> list = articleLabelLinkService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询文章、文章标签关系分页
     *
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询文章、文章标签关系分页", description = "查询文章、文章标签关系分页", method = "GET")
    public Result<IPage<ArticleLabelLinkVo>> getPage(ArticleLabelLinkDto dto) {
        IPage<ArticleLabelLinkVo> page = articleLabelLinkService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询文章、文章标签关系
     *
     * @param dto 文章、文章标签关系
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询文章、文章标签关系", description = "查询文章、文章标签关系", method = "GET")
    public Result<ArticleLabelLinkVo> getOne(ArticleLabelLinkDto dto) {
        ArticleLabelLinkVo vo = articleLabelLinkService.getOne(dto);
        return Result.success(vo);
    }
}
