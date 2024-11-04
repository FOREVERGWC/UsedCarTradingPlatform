package org.example.springboot.generator.db.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据库表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tables")
@Schema(name = "数据库表实体", description = "数据库表")
public class DbTable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 表目录
     */
    @Schema(description = "表目录")
    private String tableCatalog;
    /**
     * 数据库名
     */
    @Schema(description = "数据库名")
    private String tableSchema;
    /**
     * 表名称
     */
    @Schema(description = "表名称")
    private String tableName;
    /**
     * 表类型(BASE、TABLE、VIEW)
     */
    @Schema(description = "表类型")
    private String tableType;
    /**
     * 数据库引擎(InnoDB、MyISAM)
     */
    @Schema(description = "数据库引擎")
    private String engine;
    /**
     * 版本号
     */
    @Schema(description = "版本号")
    private Integer version;
    /**
     * 行格式(Compact、Dynamic)
     */
    @Schema(description = "行格式")
    private String rowFormat;
    /**
     * 表中的行数
     */
    @Schema(description = "表中的行数")
    private Long tableRows;
    /**
     * 平均行长度
     */
    @Schema(description = "平均行长度")
    private Long avgRowLength;
    /**
     * 数据的总长度
     */
    @Schema(description = "数据的总长度")
    private Long dataLength;
    /**
     * 数据的最大长度
     */
    @Schema(description = "数据的最大长度")
    private Long maxDataLength;
    /**
     * 索引长度
     */
    @Schema(description = "索引长度")
    private Long indexLength;
    /**
     * 空闲空间
     */
    @Schema(description = "空闲空间")
    private Long dataFree;
    /**
     * 自增长的最后一个值
     */
    @Schema(description = "自增长的最后一个值")
    private Long autoIncrement;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 最后检查时间
     */
    @Schema(description = "最后检查时间")
    private LocalDateTime checkTime;
    /**
     * 表的字符集和排序规则
     */
    @Schema(description = "表的字符集和排序规则")
    private String tableCollation;
    /**
     * 校验和
     */
    @Schema(description = "校验和")
    private Long checksum;
    /**
     * 创建选项(压缩)
     */
    @Schema(description = "创建选项")
    private String createOptions;
    /**
     * 表注释
     */
    @Schema(description = "表注释")
    private String tableComment;
}
