package org.example.springboot.domain.vo;

import lombok.*;
import org.example.springboot.domain.entity.RoleMenuLink;
import org.example.springboot.domain.entity.Role;
import org.example.springboot.domain.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 角色、菜单关系
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "角色、菜单关系实体", description = "角色、菜单关系")
public class RoleMenuLinkVo extends RoleMenuLink {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 角色
     */
    @Schema(description = "角色")
    private Role role;
    /**
     * 菜单
     */
    @Schema(description = "菜单")
    private Menu menu;
}
