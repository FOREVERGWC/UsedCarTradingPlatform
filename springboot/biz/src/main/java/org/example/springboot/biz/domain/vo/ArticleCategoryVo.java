package org.example.springboot.biz.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.biz.domain.entity.ArticleCategory;
import org.example.springboot.system.domain.entity.User;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "文章类别实体", description = "文章类别")
public class ArticleCategoryVo extends ArticleCategory {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 作者
     */
    @Schema(description = "作者")
    private User user;
    /**
     * 子类别
     */
    @Schema(description = "子类别")
    List<ArticleCategoryVo> children;
}
