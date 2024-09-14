package org.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.example.springboot.domain.entity.system.Menu;
import org.example.springboot.domain.entity.system.Permission;
import org.example.springboot.domain.entity.system.Role;
import org.example.springboot.domain.entity.system.User;
import org.example.springboot.domain.model.LoginUser;
import org.example.springboot.service.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录失败！用户名或密码错误");
        }
        // 角色列表
        List<Role> roleList = roleService.listByUserId(user.getId());
        // 菜单列表
        List<Menu> menuList = menuService.listByUserId(user.getId());
        // 权限列表
        List<Permission> permissionList = permissionService.listByUserId(user.getId());
        // 权限标识列表
        List<String> codeList = permissionList.stream()
                .map(Permission::getCode)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .toList();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(codeList);
        return new LoginUser(user, roleList, menuList, permissionList, authorities);
    }
}
