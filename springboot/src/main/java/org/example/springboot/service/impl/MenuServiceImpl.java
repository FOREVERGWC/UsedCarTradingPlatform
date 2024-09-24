package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.entity.system.Menu;
import org.example.springboot.domain.dto.MenuDto;
import org.example.springboot.domain.vo.MenuVo;
import org.example.springboot.mapper.MenuMapper;
import org.example.springboot.service.IBaseService;
import org.example.springboot.service.IMenuService;
import org.example.springboot.service.IRoleMenuLinkService;
import org.example.springboot.service.IUserRoleLinkService;
import org.example.springboot.utils.DataUtils;
import org.example.springboot.utils.ExcelUtils;
import org.example.springboot.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单服务实现类
 * </p>
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService, IBaseService<Menu> {
    @Resource
    private IUserRoleLinkService userRoleLinkService;
    @Resource
    private IRoleMenuLinkService roleMenuLinkService;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public boolean save(Menu entity) {
        entity.setStatus(UserStatus.NORMAL.getCode());
        entity.setVisible(true);
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Menu> entityList) {
        entityList.forEach(item -> {
            item.setStatus(UserStatus.NORMAL.getCode());
            item.setVisible(true);
        });
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdate(Menu entity) {
        // 祖级菜单ID
        if (entity.getParentId() == 0L) {
            entity.setAncestorId(0L);
        } else {
            Long ancestorId = DataUtils.getAncestorId(entity.getParentId(), this::getById, Menu::getParentId);
            entity.setAncestorId(ancestorId);
        }
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public List<Menu> listByUserId(Long userId) {
        List<Long> roleIdList = userRoleLinkService.listRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        List<Long> menuIdList = roleMenuLinkService.listMenuIdsByRoleIds(roleIdList);
        if (CollectionUtil.isEmpty(menuIdList)) {
            return List.of();
        }
        return Optional.ofNullable(listByIds(menuIdList)).orElse(List.of());
    }

    @Override
    public List<MenuVo> getList(MenuDto dto) {
        List<Menu> menuList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(menuList)) {
            return List.of();
        }
        // 组装VO
        return menuList.stream().map(item -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public List<MenuVo> getTree(MenuDto dto) {
        List<MenuVo> vos = getList(dto);
        // 树
        return DataUtils.listToTree(vos, MenuVo::getParentId, MenuVo::setChildren, MenuVo::getId, 0L, null, null);
    }

    @Override
    public List<MenuVo> getAuthTree() {
        Long userId = UserUtils.getLoginUserId();
        List<Long> roleIdList = userRoleLinkService.listRoleIdsByUserId(userId);
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
            return vo;
        }).toList();
        // 树
        return DataUtils.listToTree(vos,
                MenuVo::getParentId,
                MenuVo::setChildren,
                MenuVo::getId,
                0L,
                MenuVo::getSort,
                Comparator.naturalOrder());
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
            return vo;
        }).toList();
        // 树
        return DataUtils.listToTree(vos, MenuVo::getParentId, MenuVo::setChildren, MenuVo::getId, 0L, null, null);
    }

    @Override
    public IPage<MenuVo> getPage(MenuDto dto) {
        // 祖级
        dto.setAncestorId(0L);
        Page<Menu> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        Page<Menu> info = getWrapper(dto)
                .select(Menu::getId)
                .page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 子级
        List<Long> idList = info.getRecords().stream().map(Menu::getId).toList();
        List<Menu> menuList = lambdaQuery()
                .in(Menu::getId, idList)
                .or()
                .in(Menu::getAncestorId, idList)
                .list();
        // 组装VO
        List<MenuVo> vos = menuList.stream().map(item -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
        // 树
        List<MenuVo> tree = DataUtils.listToTree(
                vos,
                MenuVo::getParentId,
                MenuVo::setChildren,
                MenuVo::getId,
                0L,
                MenuVo::getSort,
                Comparator.naturalOrder()
        );
        // 组装VO
        IPage<MenuVo> convert = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        convert.setRecords(tree);
        return convert;
    }

    @Override
    public MenuVo getOne(MenuDto dto) {
        Menu one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        MenuVo vo = new MenuVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void exportExcel(Menu menu, HttpServletResponse response) {
        ExcelUtils.exportExcel(response, this, menu, Menu.class, threadPoolTaskExecutor);
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

    @Override
    public List<Menu> getPageList(Menu entity, IPage<Menu> page) {
        IPage<Menu> info = getWrapper(entity).page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return List.of();
        }
        return info.getRecords();
    }

    @Override
    public LambdaQueryChainWrapper<Menu> getWrapper(Menu entity) {
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
