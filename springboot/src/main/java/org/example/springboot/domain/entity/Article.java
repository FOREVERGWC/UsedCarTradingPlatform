package org.example.springboot.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import org.example.springboot.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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
@EqualsAndHashCode(callSuper = false)
@TableName("biz_article")
@Schema(name = "文章实体", description = "文章")
public class Article extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;
    /**
     * 类别ID
     */
    @Schema(description = "类别ID")
    private Long categoryId;
    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
    /**
     * 作者ID
     */
    @Schema(description = "作者ID")
    private Long userId;
    /**
     * 浏览量
     */
    @Schema(description = "浏览量")
    private Long viewCount;
    /**
     * 点赞量
     */
    @Schema(description = "点赞量")
    private Long likeCount;
    /**
     * 点踩量
     */
    @Schema(description = "点踩量")
    private Long dislikeCount;
    /**
     * 评论量
     */
    @Schema(description = "评论量")
    private Long commentCount;
    /**
     * 收藏量
     */
    @Schema(description = "收藏量")
    private Long collectionCount;
    /**
     * 置顶(0否、1是)
     */
    @Schema(description = "置顶(0否、1是)")
    private Boolean top;
    /**
     * 可见性(0私有、1公开)
     */
    @Schema(description = "可见性(0私有、1公开)")
    private String visible;
    /**
     * 允许评论(0否、1是)
     */
    @Schema(description = "允许评论(0否、1是)")
    private Boolean commentable;
    /**
     * 状态(0未发布、1已发布、2定时发布)
     */
    @Schema(description = "状态(0未发布、1已发布、2定时发布)")
    private String status;
    /**
     * 发布时间
     */
    @Schema(description = "发布时间")
    private LocalDateTime releaseTime;
}
