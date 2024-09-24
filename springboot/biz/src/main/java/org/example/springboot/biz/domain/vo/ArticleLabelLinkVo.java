package org.example.springboot.biz.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.biz.domain.entity.Article;
import org.example.springboot.biz.domain.entity.ArticleLabel;
import org.example.springboot.biz.domain.entity.ArticleLabelLink;

import java.io.Serial;

/**
 * <p>
 * 文章、文章标签关系
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "文章、文章标签关系实体", description = "文章、文章标签关系")
public class ArticleLabelLinkVo extends ArticleLabelLink {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 文章
     */
    @Schema(description = "文章")
    private Article article;
    /**
     * 标签
     */
    @Schema(description = "标签")
    private ArticleLabel label;
}
