package org.example.springboot.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.domain.entity.ArticleCategory;

import java.io.Serial;
import java.util.List;

/**
 * <p>
 * 文章类别
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "文章类别实体", description = "文章类别")
public class ArticleCategoryVo extends ArticleCategory {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 子类别
     */
    @Schema(description = "子类别")
    List<ArticleCategoryVo> children;
}
