package org.example.springboot.generator.db.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 键列使用情况
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName(value = "key_column_usage")
@Schema(name = "键列使用情况实体", description = "键列使用情况")
public class DbKeyColumnUsage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 约束目录
     */
    @Schema(description = "约束目录")
    private String constraintCatalog;
    /**
     * 约束架构
     */
    @Schema(description = "约束架构")
    private String constraintSchema;
    /**
     * 约束名称
     */
    @Schema(description = "约束名称")
    private String constraintName;
    /**
     * 表目录
     */
    @Schema(description = "表目录")
    private String tableCatalog;
    /**
     * 表架构
     */
    @Schema(description = "表架构")
    private String tableSchema;
    /**
     * 表名称
     */
    @Schema(description = "表名称")
    private String tableName;
    /**
     * 列名称
     */
    @Schema(description = "列名称")
    private String columnName;
    /**
     * 列的序号
     */
    @Schema(description = "列的序号")
    private Integer ordinalPosition;
    /**
     * 唯一约束中的位置
     */
    @Schema(description = "唯一约束中的位置")
    private Integer positionInUniqueConstraint;
    /**
     * 被引用的表架构
     */
    @Schema(description = "被引用的表架构")
    private String referencedTableSchema;
    /**
     * 被引用的表名称
     */
    @Schema(description = "被引用的表名称")
    private String referencedTableName;
    /**
     * 被引用的列名称
     */
    @Schema(description = "被引用的列名称")
    private String referencedColumnName;
}
