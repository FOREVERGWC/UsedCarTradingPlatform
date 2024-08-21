package org.example.springboot.domain.vo;

import org.example.springboot.domain.entity.UserRoleLink;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.entity.Role;
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
 * 用户、角色关系
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "用户、角色关系实体", description = "用户、角色关系")
public class UserRoleLinkVo extends UserRoleLink {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    @Schema(description = "用户")
    private User user;
    /**
     * 角色
     */
    @Schema(description = "角色")
    private Role role;
}
