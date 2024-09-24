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
import org.example.springboot.common.enums.DeleteEnum;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.dto.RoleDto;
import org.example.springboot.domain.entity.system.Role;
import org.example.springboot.domain.entity.system.UserRoleLink;
import org.example.springboot.domain.vo.RoleVo;
import org.example.springboot.mapper.RoleMapper;
import org.example.springboot.service.IBaseService;
import org.example.springboot.service.IRoleService;
import org.example.springboot.service.IUserRoleLinkService;
import org.example.springboot.utils.ExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色服务实现类
 * </p>
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService, IBaseService<Role> {
    @Resource
    private IUserRoleLinkService userRoleLinkService;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public boolean save(Role entity) {
        entity.setStatus(UserStatus.NORMAL.getCode());
        entity.setDeleted(DeleteEnum.NORMAL.getCode());
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Role> entityList) {
        entityList.forEach(item -> {
            item.setStatus(UserStatus.NORMAL.getCode());
            item.setDeleted(DeleteEnum.NORMAL.getCode());
        });
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdate(Role entity) {
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Transactional
    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        List<UserRoleLink> linkList = userRoleLinkService.listByRoleIds(list);
        Map<Long, Integer> linkMap = linkList.stream()
                .collect(Collectors.groupingBy(UserRoleLink::getRoleId, Collectors.summingInt(e -> 1)));
        boolean flag = linkMap.values().stream().anyMatch(count -> count > 0);
        if (flag) {
            throw new CustomException(ResultCode.ROLE_DELETE_ERROR);
        }
        return super.removeBatchByIds(list);
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        List<Long> roleIdList = userRoleLinkService.listRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        return Optional.ofNullable(listByIds(roleIdList)).orElse(List.of());
    }

    @Override
    public Map<Long, List<Long>> mapRoleIdsByUserIds(List<Long> userIds) {
        List<UserRoleLink> linkList = userRoleLinkService.listByUserIds(userIds);
        return linkList.stream()
                .collect(Collectors.groupingBy(
                        UserRoleLink::getUserId,
                        Collectors.mapping(UserRoleLink::getRoleId, Collectors.toList())
                ));
    }

    @Override
    public Map<Long, List<Role>> mapByUserIds(List<Long> userIds) {
        List<UserRoleLink> linkList = userRoleLinkService.listByUserIds(userIds);
        List<Long> roleIdList = linkList.stream().map(UserRoleLink::getRoleId).distinct().toList();
        if (CollectionUtil.isEmpty(roleIdList)) {
            return Map.of();
        }
        List<Role> roleList = Optional.ofNullable(listByIds(roleIdList)).orElse(List.of());
        return linkList.stream()
                .collect(Collectors.groupingBy(
                        UserRoleLink::getUserId,
                        Collectors.mapping(
                                link -> roleList.stream()
                                        .filter(role -> Objects.equals(role.getId(), link.getRoleId()))
                                        .findFirst()
                                        .orElse(null),
                                Collectors.filtering(Objects::nonNull, Collectors.toList())
                        )
                ));
    }

    @Override
    public List<RoleVo> getList(RoleDto dto) {
        List<Role> roleList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(roleList)) {
            return List.of();
        }
        // 组装VO
        return roleList.stream().map(item -> {
            RoleVo vo = new RoleVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public IPage<RoleVo> getPage(RoleDto dto) {
        Page<Role> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            RoleVo vo = new RoleVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public RoleVo getOne(RoleDto dto) {
        Role one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        RoleVo vo = new RoleVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void exportExcel(Role role, HttpServletResponse response) {
        ExcelUtils.exportExcel(response, this, role, Role.class, threadPoolTaskExecutor);
    }

    @Override
    public void handleStatus(Long id) {
        Role role = getById(id);
        if (role == null) {
            throw new CustomException(ResultCode.ROLE_NOT_FOUND_ERROR);
        }
        if (Objects.equals(UserStatus.NORMAL.getCode(), role.getStatus())) {
            role.setStatus(UserStatus.DISABLE.getCode());
        } else {
            role.setStatus(UserStatus.NORMAL.getCode());
        }
        updateById(role);
    }

    @Override
    public List<Role> getPageList(Role entity, IPage<Role> page) {
        IPage<Role> info = getWrapper(entity).page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return List.of();
        }
        return info.getRecords();
    }

    @Override
    public LambdaQueryChainWrapper<Role> getWrapper(Role entity) {
        LambdaQueryChainWrapper<Role> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Role::getId, entity.getId())
                .like(StrUtil.isNotBlank(entity.getName()), Role::getName, entity.getName())
                .eq(entity.getSort() != null, Role::getSort, entity.getSort())
                .like(StrUtil.isNotBlank(entity.getStatus()), Role::getStatus, entity.getStatus())
                .eq(entity.getDeleted() != null, Role::getDeleted, entity.getDeleted())
                .orderByAsc(Role::getSort);
        if (entity instanceof RoleDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    Role::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
