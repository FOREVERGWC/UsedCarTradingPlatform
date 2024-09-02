package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.domain.entity.RoleMenuLink;
import org.example.springboot.domain.entity.Role;
import org.example.springboot.domain.entity.Menu;
import org.example.springboot.domain.dto.RoleMenuLinkDto;
import org.example.springboot.domain.vo.RoleMenuLinkVo;
import org.example.springboot.mapper.RoleMenuLinkMapper;
import org.example.springboot.service.IRoleMenuLinkService;
import org.example.springboot.service.IRoleService;
import org.example.springboot.service.IMenuService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色、菜单关系服务实现类
 * </p>
 */
@Service
public class RoleMenuLinkServiceImpl extends ServiceImpl<RoleMenuLinkMapper, RoleMenuLink> implements IRoleMenuLinkService {
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuService menuService;

    @Override
    public List<RoleMenuLinkVo> getList(RoleMenuLinkDto dto) {
        List<RoleMenuLink> roleMenuLinkList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(roleMenuLinkList)) {
            return List.of();
        }
        // 角色
        List<Long> roleIdList = roleMenuLinkList.stream().map(RoleMenuLink::getRoleId).toList();
        List<Role> roleList = roleService.listByIds(roleIdList);
        Map<Long, Role> roleMap = roleList.stream().collect(Collectors.toMap(Role::getId, item -> item));
        // 菜单
        List<Long> menuIdList = roleMenuLinkList.stream().map(RoleMenuLink::getMenuId).toList();
        List<Menu> menuList = menuService.listByIds(menuIdList);
        Map<Long, Menu> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getId, item -> item));
        // 组装VO
        return roleMenuLinkList.stream().map(item -> {
            RoleMenuLinkVo vo = new RoleMenuLinkVo();
            BeanUtils.copyProperties(item, vo);
            vo.setRole(roleMap.getOrDefault(item.getRoleId(), Role.builder().name("已删除").build()));
            vo.setMenu(menuMap.getOrDefault(item.getMenuId(), Menu.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<RoleMenuLinkVo> getPage(RoleMenuLinkDto dto) {
        Page<RoleMenuLink> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 角色
        List<Long> roleIdList = info.getRecords().stream().map(RoleMenuLink::getRoleId).toList();
        List<Role> roleList = roleService.listByIds(roleIdList);
        Map<Long, Role> roleMap = roleList.stream().collect(Collectors.toMap(Role::getId, item -> item));
        // 菜单
        List<Long> menuIdList = info.getRecords().stream().map(RoleMenuLink::getMenuId).toList();
        List<Menu> menuList = menuService.listByIds(menuIdList);
        Map<Long, Menu> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            RoleMenuLinkVo vo = new RoleMenuLinkVo();
            BeanUtils.copyProperties(item, vo);
            vo.setRole(roleMap.getOrDefault(item.getRoleId(), Role.builder().name("已删除").build()));
            vo.setMenu(menuMap.getOrDefault(item.getMenuId(), Menu.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public RoleMenuLinkVo getOne(RoleMenuLinkDto dto) {
        RoleMenuLink one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 角色
        Role role = Optional.ofNullable(roleService.getById(one.getRoleId())).orElse(Role.builder().name("已删除").build());
        // 菜单
        Menu menu = Optional.ofNullable(menuService.getById(one.getMenuId())).orElse(Menu.builder().name("已删除").build());
        // 组装VO
        RoleMenuLinkVo vo = new RoleMenuLinkVo();
        BeanUtils.copyProperties(one, vo);
        vo.setRole(role);
        vo.setMenu(menu);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 角色、菜单关系
     * @return 结果
     */
    private LambdaQueryChainWrapper<RoleMenuLink> getWrapper(RoleMenuLink entity) {
        LambdaQueryChainWrapper<RoleMenuLink> wrapper = lambdaQuery()
                .eq(entity.getId() != null, RoleMenuLink::getId, entity.getId())
                .eq(entity.getRoleId() != null, RoleMenuLink::getRoleId, entity.getRoleId())
                .eq(entity.getMenuId() != null, RoleMenuLink::getMenuId, entity.getMenuId());
        if (entity instanceof RoleMenuLinkDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                RoleMenuLink::getCreateTime,
                startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
