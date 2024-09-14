package org.example.springboot.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.domain.entity.Article;
import org.example.springboot.domain.entity.ArticleCategory;
import org.example.springboot.domain.entity.system.User;

import java.io.Serial;

/**
 * <p>
 * 文章
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "文章实体", description = "文章")
public class ArticleVo extends Article {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 类别
     */
    @Schema(description = "类别")
    private ArticleCategory category;
    /**
     * 作者
     */
    @Schema(description = "作者")
    private User user;
    /**
     * 可见性
     */
    @Schema(description = "可见性")
    private String visibleText;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private String statusText;
}
