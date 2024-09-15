package org.example.springboot.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.domain.entity.system.Menu;
import org.example.springboot.domain.entity.system.Permission;
import org.example.springboot.domain.entity.system.Role;
import org.example.springboot.domain.entity.system.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 登录用户
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "登录用户实体", description = "登录用户")
public class LoginUser extends User implements UserDetails {
    /**
     * 账户锁定状态
     */
    @Schema(description = "账户锁定状态")
    private Boolean accountNonLocked;
    /**
     * 令牌
     */
    @Schema(description = "令牌")
    private String token;
    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    private List<Role> roleList;
    /**
     * 菜单列表
     */
    @Schema(description = "菜单列表")
    private List<Menu> menuList;
    /**
     * 权限列表
     */
    @Schema(description = "权限列表")
    private List<Permission> permissionList;
    /**
     * 权限标识列表
     */
    @Schema(description = "权限标识列表")
    private Collection<? extends GrantedAuthority> authorities;

    public LoginUser(User user, Boolean accountNonLocked, List<Role> roleList, List<Menu> menuList, List<Permission> permissionList, Collection<? extends GrantedAuthority> authorities) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getNickname(), user.getName(), user.getAvatar(), user.getGender(), user.getBirthday(), user.getStatus(), user.getPhone(), user.getEmail(), user.getOpenId(), user.getBalance(), user.getLoginIp(), user.getLoginTime(), user.getCreateBy(), user.getCreateTime(), user.getUpdateBy(), user.getUpdateTime(), user.getRemark());
        this.accountNonLocked = accountNonLocked;
        this.roleList = roleList;
        this.menuList = menuList;
        this.permissionList = permissionList;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.getStatus(), UserStatus.NORMAL.getCode());
    }
}
