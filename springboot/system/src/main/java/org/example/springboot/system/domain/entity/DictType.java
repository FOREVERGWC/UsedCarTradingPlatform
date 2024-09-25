package org.example.springboot.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.alibaba.excel.annotation.ExcelProperty;
import org.example.springboot.system.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 字典类型
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_type")
@Schema(name = "字典类型实体", description = "字典类型")
public class DictType extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @ExcelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    @ExcelProperty(value = "字典名称")
    private String name;
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    @ExcelProperty(value = "字典类型")
    private String type;
    /**
     * 字典状态(0禁用、1正常)
     */
    @Schema(description = "字典状态(0禁用、1正常)")
    @ExcelProperty(value = "字典状态(0禁用、1正常)")
    private String status;
}
