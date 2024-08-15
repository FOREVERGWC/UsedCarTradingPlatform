package org.example.springboot.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.domain.BaseEntity;

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
@EqualsAndHashCode(callSuper = false)
@TableName("biz_article_label_link")
@Schema(name = "文章、文章标签关系实体", description = "文章、文章标签关系")
public class ArticleLabelLink extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 文章ID
     */
    @Schema(description = "文章ID")
    private Long articleId;
    /**
     * 标签ID
     */
    @Schema(description = "标签ID")
    private Long labelId;
}
