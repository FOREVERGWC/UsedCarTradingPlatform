package org.example.springboot.domain.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.example.springboot.common.annotation.Dict;
import org.example.springboot.common.enums.MenuType;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 菜单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
@Schema(name = "菜单实体", description = "菜单")
public class Menu extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;
    /**
     * 父级菜单ID
     */
    @Schema(description = "父级菜单ID")
    private Long parentId;
    /**
     * 祖级菜单ID
     */
    @Schema(description = "祖级菜单ID")
    private Long ancestorId;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;
    /**
     * 类型(1目录、2菜单、3按钮)
     */
    @Dict(enumClass = MenuType.class)
    @Schema(description = "类型(1目录、2菜单、3按钮)")
    private String type;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 状态(0禁用、1正常)
     */
    @Dict(enumClass = UserStatus.class)
    @Schema(description = "状态(0禁用、1正常)")
    private String status;
    /**
     * 可见(0否、1是)
     */
    @Schema(description = "可见(0否、1是)")
    private Boolean visible;
}
