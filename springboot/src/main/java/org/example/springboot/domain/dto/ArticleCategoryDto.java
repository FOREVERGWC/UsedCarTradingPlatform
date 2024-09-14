package org.example.springboot.domain.dto;

import lombok.*;
import org.example.springboot.domain.entity.ArticleCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.Map;

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
@EqualsAndHashCode(callSuper = true)
@Schema(name = "文章类别实体", description = "文章类别")
public class ArticleCategoryDto extends ArticleCategory {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 页码
     */
    @JsonIgnore
    private Integer pageNo;
    /**
     * 页面大小
     */
    @JsonIgnore
    private Integer pageSize;
    /**
     * 查询参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
}
