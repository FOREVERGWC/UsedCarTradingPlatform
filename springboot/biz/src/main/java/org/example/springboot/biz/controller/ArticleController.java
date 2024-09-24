package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.system.domain.Result;
import org.example.springboot.biz.domain.dto.ArticleDto;
import org.example.springboot.biz.domain.entity.Article;
import org.example.springboot.biz.domain.vo.ArticleVo;
import org.example.springboot.biz.service.IArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章前端控制器
 * </p>
 */
@RestController
@RequestMapping("/article")
@Tag(name = "文章", description = "文章")
public class ArticleController {
    @Resource
    private IArticleService articleService;

    /**
     * 添加、修改文章
     *
     * @param article 文章
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改文章", description = "添加、修改文章", method = "POST")
    public Result<Void> save(@RequestBody Article article) {
        articleService.saveOrUpdate(article);
        return Result.success();
    }

    /**
     * 删除文章
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除文章", description = "删除文章", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        articleService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询文章列表
     *
     * @param dto 文章
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章列表", description = "查询文章列表", method = "GET")
    public Result<List<ArticleVo>> getList(ArticleDto dto) {
        List<ArticleVo> list = articleService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询文章分页
     *
     * @param dto 文章
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询文章分页", description = "查询文章分页", method = "GET")
    public Result<IPage<ArticleVo>> getPage(ArticleDto dto) {
        IPage<ArticleVo> page = articleService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询文章
     *
     * @param dto 文章
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询文章", description = "查询文章", method = "GET")
    public Result<ArticleVo> getOne(ArticleDto dto) {
        ArticleVo vo = articleService.getOne(dto);
        return Result.success(vo);
    }

    /**
     * 置顶或取消置顶文章
     *
     * @param id 文章ID
     * @return 结果
     */
    @PutMapping("/top/{id}")
    @Operation(summary = "置顶或取消置顶文章", description = "置顶或取消置顶文章", method = "PUT")
    public Result<Void> handleTop(@PathVariable Long id) {
        articleService.handleTop(id);
        return Result.success();
    }

    /**
     * 允许或禁止文章评论
     *
     * @param id 文章ID
     * @return 结果
     */
    @PutMapping("/comment/{id}")
    @Operation(summary = "允许或禁止文章评论", description = "允许或禁止文章评论", method = "PUT")
    public Result<Void> handleComment(@PathVariable Long id) {
        articleService.handleComment(id);
        return Result.success();
    }
}
