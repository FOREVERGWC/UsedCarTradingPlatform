package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.entity.Permission;
import org.example.springboot.domain.dto.PermissionDto;
import org.example.springboot.domain.vo.PermissionVo;
import org.example.springboot.mapper.PermissionMapper;
import org.example.springboot.service.IPermissionService;
import org.example.springboot.service.IRolePermissionLinkService;
import org.example.springboot.service.IUserRoleLinkService;
import org.example.springboot.utils.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 权限服务实现类
 * </p>
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Resource
    private IRolePermissionLinkService rolePermissionLinkService;
    @Resource
    private IUserRoleLinkService userRoleLinkService;

    @Override
    public boolean save(Permission entity) {
        entity.setStatus(UserStatus.NORMAL.getCode());
        return super.save(entity);
    }

    @Override
    public boolean saveOrUpdate(Permission entity) {
        // 祖级权限ID
        if (entity.getParentId() == 0L) {
            entity.setAncestorId(0L);
        } else {
            Long ancestorId = DataUtils.getAncestorId(entity.getParentId(), this::getById, Permission::getParentId);
            entity.setAncestorId(ancestorId);
        }
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public List<Permission> listByUserId(Long userId) {
        List<Long> roleIdList = userRoleLinkService.listRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        List<Long> permissionIdList = rolePermissionLinkService.listPermissionIdsByRoleIds(roleIdList);
        if (CollectionUtil.isEmpty(permissionIdList)) {
            return List.of();
        }
        return Optional.ofNullable(listByIds(permissionIdList)).orElse(List.of());
    }

    @Override
    public List<PermissionVo> getList(PermissionDto dto) {
        List<Permission> permissionList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(permissionList)) {
            return List.of();
        }
        // 组装VO
        return permissionList.stream().map(item -> {
            PermissionVo vo = new PermissionVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public List<PermissionVo> getTree(PermissionDto dto) {
        List<PermissionVo> vos = getList(dto);
        // 树
        return DataUtils.listToTree(vos, PermissionVo::getParentId, PermissionVo::setChildren, PermissionVo::getId, 0L, null, null);
    }

    @Override
    public List<PermissionVo> getRoleTree(Long roleId) {
        List<Long> permissionIdList = rolePermissionLinkService.listPermissionIdsByRoleId(roleId);
        if (CollectionUtil.isEmpty(permissionIdList)) {
            return List.of();
        }
        List<Permission> permissionList = listByIds(permissionIdList);
        if (CollectionUtil.isEmpty(permissionIdList)) {
            return List.of();
        }
        List<PermissionVo> vos = permissionList.stream().map(item -> {
            PermissionVo vo = new PermissionVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
        // 树
        return DataUtils.listToTree(vos, PermissionVo::getParentId, PermissionVo::setChildren, PermissionVo::getId, 0L, null, null);
    }

    @Override
    public IPage<PermissionVo> getPage(PermissionDto dto) {
        // 祖级
        dto.setAncestorId(0L);
        Page<Permission> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        Page<Permission> info = getWrapper(dto)
                .select(Permission::getId)
                .page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 子级
        List<Long> idList = info.getRecords().stream().map(Permission::getId).toList();
        List<Permission> permissionList = lambdaQuery()
                .in(Permission::getId, idList)
                .or()
                .in(Permission::getAncestorId, idList)
                .list();
        // 组装VO
        List<PermissionVo> vos = permissionList.stream().map(item -> {
            PermissionVo vo = new PermissionVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
        // 树
        List<PermissionVo> tree = DataUtils.listToTree(
                vos,
                PermissionVo::getParentId,
                PermissionVo::setChildren,
                PermissionVo::getId,
                0L,
                PermissionVo::getSort,
                Comparator.naturalOrder()
        );
        // 组装VO
        IPage<PermissionVo> convert = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        convert.setRecords(tree);
        return convert;
    }

    @Override
    public PermissionVo getOne(PermissionDto dto) {
        Permission one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        PermissionVo vo = new PermissionVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void handleStatus(Long id) {
        Permission permission = getById(id);
        if (permission == null) {
            throw new CustomException(ResultCode.PERMISSION_NOT_FOUND_ERROR);
        }
        if (Objects.equals(UserStatus.NORMAL.getCode(), permission.getStatus())) {
            permission.setStatus(UserStatus.DISABLE.getCode());
        } else {
            permission.setStatus(UserStatus.NORMAL.getCode());
        }
        updateById(permission);
    }

    /**
     * 组装查询包装器
     *
     * @param entity 权限
     * @return 结果
     */
    private LambdaQueryChainWrapper<Permission> getWrapper(Permission entity) {
        LambdaQueryChainWrapper<Permission> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Permission::getId, entity.getId())
                .like(StrUtil.isNotBlank(entity.getName()), Permission::getName, entity.getName())
                .like(StrUtil.isNotBlank(entity.getCode()), Permission::getCode, entity.getCode())
                .eq(entity.getParentId() != null, Permission::getParentId, entity.getParentId())
                .eq(entity.getAncestorId() != null, Permission::getAncestorId, entity.getAncestorId())
                .eq(entity.getSort() != null, Permission::getSort, entity.getSort())
                .eq(entity.getStatus() != null, Permission::getStatus, entity.getStatus())
                .orderByAsc(Permission::getSort);
        if (entity instanceof PermissionDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    Permission::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
