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
 * 代码生成业务字段
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("gen_table_column")
@Schema(name = "代码生成业务字段实体", description = "代码生成业务字段")
public class GenTableColumn extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 5591445525713050168L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 归属表ID
     */
    @Schema(description = "归属表ID")
    private Long tableId;
//    /**
//     * 归属表名
//     */
//    @Schema(description = "归属表名")
//    private String tableName;
    /**
     * 列名称
     */
    @Schema(description = "列名称")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 列类型
     */
    @Schema(description = "列类型")
    private String type;
    /**
     * JAVA类型
     */
    @Schema(description = "JAVA类型")
    private String javaType;
    /**
     * JAVA字段名
     */
    @Schema(description = "JAVA字段名")
    private String javaField;
    /**
     * 是否主键(0否、1是)
     */
    @Schema(description = "是否主键")
    private Boolean isPk;
    /**
     * 是否自增(0否、1是)
     */
    @Schema(description = "是否自增")
    private Boolean isIncrement;
    /**
     * 是否必填(0否、1是)
     */
    @Schema(description = "是否必填")
    private Boolean isRequired;
    /**
     * 是否插入字段(0否、1是)
     */
    @Schema(description = "是否插入字段")
    private Boolean isInsert;
    /**
     * 是否编辑字段(0否、1是)
     */
    @Schema(description = "是否编辑字段")
    private Boolean isEdit;
    /**
     * 是否列表字段(0否、1是)
     */
    @Schema(description = "是否列表字段")
    private Boolean isList;
    /**
     * 是否查询字段(0否、1是)
     */
    @Schema(description = "是否查询字段")
    private Boolean isQuery;
    /**
     * 查询方式(1=、2!=、3>、4>=、5<、6<=、7LIKE、8BETWEEN)
     */
    @Schema(description = "查询方式")
    private String queryType;
    /**
     * 显示类型(1文本框、2文本域、3下拉框、4开关、5单选框、6复选框、7日期控件、8图片上传、9文件上传、10富文本控件)
     */
    @Schema(description = "显示类型")
    private String htmlType;
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;
}
