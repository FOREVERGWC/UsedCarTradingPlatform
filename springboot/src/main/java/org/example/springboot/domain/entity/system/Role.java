package org.example.springboot.domain.entity.system;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.common.annotation.Dict;
import org.example.springboot.common.converter.EnableStatusConverter;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.domain.BaseEntity;

import java.io.Serial;

/**
 * <p>
 * 角色
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
@Schema(name = "角色实体", description = "角色")
public class Role extends BaseEntity {
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
     * 角色名称
     */
    @Schema(description = "角色名称")
    @ExcelProperty(value = "角色名称")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    @ExcelProperty(value = "排序")
    private Integer sort;
    /**
     * 状态(0禁用、1正常)
     */
    @Schema(description = "状态(0禁用、1正常)")
    @ExcelProperty(value = "状态", converter = EnableStatusConverter.class)
    @Dict(enumClass = UserStatus.class)
    private String status;
    /**
     * 逻辑删除(0正常、1删除)
     */
    @Schema(description = "逻辑删除(0正常、1删除)")
    @ExcelIgnore
    private Integer deleted;
}
