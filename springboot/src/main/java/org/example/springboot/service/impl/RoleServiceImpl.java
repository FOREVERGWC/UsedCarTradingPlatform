package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.common.enums.DeleteEnum;
import org.example.springboot.common.enums.ResultCode;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.common.exception.CustomException;
import org.example.springboot.domain.dto.RoleDto;
import org.example.springboot.domain.entity.Role;
import org.example.springboot.domain.entity.UserRoleLink;
import org.example.springboot.domain.vo.RoleVo;
import org.example.springboot.mapper.RoleMapper;
import org.example.springboot.service.IRoleService;
import org.example.springboot.service.IUserRoleLinkService;
import org.springframework.beans.BeanUtils;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private IUserRoleLinkService userRoleLinkService;

    @Override
    public boolean saveOrUpdate(Role entity) {
        if (entity.getId() == null) {
            entity.setStatus(UserStatus.NORMAL.getCode());
            entity.setDeleted(DeleteEnum.NORMAL.getCode());
            return super.save(entity);
        }
        return super.saveOrUpdate(entity);
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
        List<UserRoleLink> linkList = userRoleLinkService.listByUserId(userId);
        List<Long> roleIdList = linkList.stream().map(UserRoleLink::getRoleId).toList();
        if (CollectionUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        return Optional.ofNullable(listByIds(roleIdList)).orElse(List.of());
    }

//    @Override
//    public List<Long> listRoleIdsByUserId(Long userId) {
//        List<UserRoleLink> linkList = userRoleLinkService.listByUserId(userId);
//        return linkList.stream().map(UserRoleLink::getRoleId).toList();
//    }

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

    /**
     * 组装查询包装器
     *
     * @param dto 角色
     * @return 结果
     */
    private LambdaQueryChainWrapper<Role> getWrapper(RoleDto dto) {
        Map<String, Object> params = dto.getParams();
        // 创建时间
        Object startCreateTime = params == null ? null : params.get("startCreateTime");
        Object endCreateTime = params == null ? null : params.get("endCreateTime");
        return lambdaQuery()
                .eq(dto.getId() != null, Role::getId, dto.getId())
                .like(StrUtil.isNotBlank(dto.getName()), Role::getName, dto.getName())
                .eq(dto.getSort() != null, Role::getSort, dto.getSort())
                .like(StrUtil.isNotBlank(dto.getStatus()), Role::getStatus, dto.getStatus())
                .eq(dto.getDeleted() != null, Role::getDeleted, dto.getDeleted())
                .between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime), Role::getCreateTime, startCreateTime, endCreateTime)
                .orderByAsc(Role::getSort);
    }
}
