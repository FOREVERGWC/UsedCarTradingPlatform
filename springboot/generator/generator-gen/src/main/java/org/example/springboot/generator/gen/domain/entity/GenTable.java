package org.example.springboot.generator.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.common.domain.BaseEntity;

import java.io.Serial;

/**
 * <p>
 * 代码生成业务表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table")
@Schema(name = "代码生成业务实体", description = "代码生成业务")
public class GenTable extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 8679071412928818866L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 表名
     */
    @Schema(description = "表名")
    private String name;
    /**
     * 类名
     */
    @Schema(description = "类名")
    private String className;
    /**
     * 生成包路径
     */
    @Schema(description = "生成包路径")
    private String packageName;
    /**
     * 生成模块名
     */
    @Schema(description = "生成模块名")
    private String moduleName;
    /**
     * 生成业务名
     */
    @Schema(description = "生成业务名")
    private String bizName;
    /**
     * 作者
     */
    @Schema(description = "作者")
    private String author;
    /**
     * 生成方式(0压缩包、1指定路径)
     */
    @Schema(description = "生成方式(0压缩包、1指定路径)")
    private Integer type;
    /**
     * 生成路径
     */
    @Schema(description = "生成路径")
    private String path;
}
