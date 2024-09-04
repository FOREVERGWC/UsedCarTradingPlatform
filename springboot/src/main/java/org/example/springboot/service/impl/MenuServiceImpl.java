package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.common.BaseContext;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.entity.Menu;
import org.example.springboot.domain.dto.MenuDto;
import org.example.springboot.domain.entity.Role;
import org.example.springboot.domain.vo.MenuVo;
import org.example.springboot.domain.vo.UserVo;
import org.example.springboot.mapper.MenuMapper;
import org.example.springboot.service.IMenuService;
import org.example.springboot.service.IRoleMenuLinkService;
import org.example.springboot.service.IUserRoleLinkService;
import org.example.springboot.utils.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 菜单服务实现类
 * </p>
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private IUserRoleLinkService userRoleLinkService;
    @Resource
    private IRoleMenuLinkService roleMenuLinkService;

    @Override
    public boolean save(Menu entity) {
        entity.setStatus(UserStatus.NORMAL.getCode());
        return super.save(entity);
    }

    @Override
    public boolean saveOrUpdate(Menu entity) {
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public List<MenuVo> getList(MenuDto dto) {
        List<Menu> menuList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(menuList)) {
            return List.of();
        }
//        // 父级菜单
//        List<Long> parentIdList = menuList.stream().map(Menu::getParentId).toList();
//        List<Parent> parentList = parentService.listByIds(parentIdList);
//        Map<Long, Parent> parentMap = parentList.stream().collect(Collectors.toMap(Parent::getId, item -> item));
        // 组装VO
        return menuList.stream().map(item -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setParent(parentMap.getOrDefault(item.getParentId(), Parent.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public List<MenuVo> getTree(MenuDto dto) {
        List<MenuVo> vos = getList(dto);
        // 树
        return DataUtils.listToTree(vos, MenuVo::getParentId, MenuVo::setChildren, MenuVo::getId, 0L);
    }

    @Override
    public List<MenuVo> getAuthTree() {
        UserVo user = BaseContext.getUser();
        List<Long> roleIdList = userRoleLinkService.listRoleIdsByUserId(user.getId());
        if (CollectionUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        List<Long> menuIdList = roleMenuLinkService.listMenuIdsByRoleIds(roleIdList);
        if (CollectionUtil.isEmpty(menuIdList)) {
            return List.of();
        }
        List<Menu> menuList = listByIds(menuIdList);
        if (CollectionUtil.isEmpty(menuIdList)) {
            return List.of();
        }
        List<MenuVo> vos = menuList.stream().map(item -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setParent(parentMap.getOrDefault(item.getParentId(), Parent.builder().name("已删除").build()));
            return vo;
        }).toList();
        // 树
        return DataUtils.listToTree(vos, MenuVo::getParentId, MenuVo::setChildren, MenuVo::getId, 0L);
    }

    @Override
    public List<MenuVo> getRoleTree(Long roleId) {
        List<Long> menuIdList = roleMenuLinkService.listMenuIdsByRoleId(roleId);
        if (CollectionUtil.isEmpty(menuIdList)) {
            return List.of();
        }
        List<Menu> menuList = listByIds(menuIdList);
        if (CollectionUtil.isEmpty(menuIdList)) {
            return List.of();
        }
        List<MenuVo> vos = menuList.stream().map(item -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setParent(parentMap.getOrDefault(item.getParentId(), Parent.builder().name("已删除").build()));
            return vo;
        }).toList();
        // 树
        return DataUtils.listToTree(vos, MenuVo::getParentId, MenuVo::setChildren, MenuVo::getId, 0L);
    }

    @Override
    public IPage<MenuVo> getPage(MenuDto dto) {
        Page<Menu> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
//        // 父级菜单
//        List<Long> parentIdList = info.getRecords().stream().map(Menu::getParentId).toList();
//        List<Parent> parentList = parentService.listByIds(parentIdList);
//        Map<Long, Parent> parentMap = parentList.stream().collect(Collectors.toMap(Parent::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setParent(parentMap.getOrDefault(item.getParentId(), Parent.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public MenuVo getOne(MenuDto dto) {
        Menu one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
//        // 父级菜单
//        Parent parent = Optional.ofNullable(parentService.getById(one.getParentId())).orElse(Parent.builder().name("已删除").build());
        // 组装VO
        MenuVo vo = new MenuVo();
        BeanUtils.copyProperties(one, vo);
//        vo.setParent(parent);
        return vo;
    }

    @Override
    public void handleStatus(Long id) {
        Menu menu = getById(id);
        if (menu == null) {
            throw new CustomException(ResultCode.MENU_NOT_FOUND_ERROR);
        }
        if (Objects.equals(UserStatus.NORMAL.getCode(), menu.getStatus())) {
            menu.setStatus(UserStatus.DISABLE.getCode());
        } else {
            menu.setStatus(UserStatus.NORMAL.getCode());
        }
        updateById(menu);
    }

    @Override
    public void handleVisible(Long id) {
        Menu menu = getById(id);
        if (menu == null) {
            throw new CustomException(ResultCode.MENU_NOT_FOUND_ERROR);
        }
        menu.setVisible(!menu.getVisible());
        updateById(menu);
    }

    /**
     * 组装查询包装器
     *
     * @param entity 菜单
     * @return 结果
     */
    private LambdaQueryChainWrapper<Menu> getWrapper(Menu entity) {
        LambdaQueryChainWrapper<Menu> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Menu::getId, entity.getId())
                .like(StrUtil.isNotBlank(entity.getName()), Menu::getName, entity.getName())
                .like(StrUtil.isNotBlank(entity.getIcon()), Menu::getIcon, entity.getIcon())
                .eq(entity.getParentId() != null, Menu::getParentId, entity.getParentId())
                .like(StrUtil.isNotBlank(entity.getPath()), Menu::getPath, entity.getPath())
                .like(StrUtil.isNotBlank(entity.getComponent()), Menu::getComponent, entity.getComponent())
                .eq(entity.getType() != null, Menu::getType, entity.getType())
                .eq(entity.getSort() != null, Menu::getSort, entity.getSort())
                .eq(entity.getStatus() != null, Menu::getStatus, entity.getStatus())
                .eq(entity.getVisible() != null, Menu::getVisible, entity.getVisible())
                .orderByAsc(Menu::getSort);
        if (entity instanceof MenuDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    Menu::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
