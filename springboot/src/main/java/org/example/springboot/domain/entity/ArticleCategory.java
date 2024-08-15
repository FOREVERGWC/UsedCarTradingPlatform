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
 * 文章类别
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("biz_article_category")
@Schema(name = "文章类别实体", description = "文章类别")
public class ArticleCategory extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String name;
    /**
     * 父级分类ID
     */
    @Schema(description = "父级分类ID")
    private Long parentId;
    /**
     * 逻辑删除(0正常、1删除)
     */
    @Schema(description = "逻辑删除(0正常、1删除)")
    private Integer deleted;
}
