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
 * 数据库列
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName(value = "columns")
@Schema(name = "数据库列实体", description = "数据库列")
public class DbTableColumn implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
     * 表名
     */
    @Schema(description = "表名")
    private String tableName;
    /**
     * 列名
     */
    @Schema(description = "列名")
    private String columnName;
    /**
     * 列的顺序位置
     */
    @Schema(description = "列的顺序位置")
    private Integer ordinalPosition;
    /**
     * 列的默认值
     */
    @Schema(description = "列的默认值")
    private String columnDefault;
    /**
     * 列是否允许空值
     */
    @Schema(description = "列是否允许空值")
    private String isNullable;
    /**
     * 数据类型
     */
    @Schema(description = "数据类型")
    private String dataType;
    /**
     * 字符最大长度
     */
    @Schema(description = "字符最大长度")
    private Long characterMaximumLength;
    /**
     * 字节最大长度
     */
    @Schema(description = "字节最大长度")
    private Long characterOctetLength;
    /**
     * 数字精度
     */
    @Schema(description = "数字精度")
    private Long numericPrecision;
    /**
     * 数字刻度
     */
    @Schema(description = "数字刻度")
    private Long numericScale;
    /**
     * 日期时间精度
     */
    @Schema(description = "日期时间精度")
    private Integer datetimePrecision;
    /**
     * 字符集名称
     */
    @Schema(description = "字符集名称")
    private String characterSetName;
    /**
     * 校对名称
     */
    @Schema(description = "校对名称")
    private String collationName;
    /**
     * 列类型
     */
    @Schema(description = "列类型")
    private String columnType;
    /**
     * 列键
     */
    @Schema(description = "列键")
    private String columnKey;
    /**
     * 额外信息
     */
    @Schema(description = "额外信息")
    private String extra;
    /**
     * 权限
     */
    @Schema(description = "权限")
    private String privileges;
    /**
     * 列注释
     */
    @Schema(description = "列注释")
    private String columnComment;
    /**
     * 生成表达式
     */
    @Schema(description = "生成表达式")
    private String generationExpression;
    /**
     * 空间参考系统ID
     */
    @Schema(description = "空间参考系统ID")
    private Integer srsId;
}
