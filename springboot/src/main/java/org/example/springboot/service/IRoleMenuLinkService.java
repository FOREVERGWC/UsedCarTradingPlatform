package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.RoleMenuLinkDto;
import org.example.springboot.domain.entity.RoleMenuLink;
import org.example.springboot.domain.vo.RoleMenuLinkVo;

import java.util.List;

/**
 * <p>
 * 角色、菜单关系服务类
 * </p>
 */
public interface IRoleMenuLinkService extends IService<RoleMenuLink> {
    /**
     * 查询角色、菜单关系列表
     *
     * @param dto 角色、菜单关系
     * @return 结果
     */
    List<RoleMenuLinkVo> getList(RoleMenuLinkDto dto);

    /**
     * 查询角色、菜单关系分页
     *
     * @param dto 角色、菜单关系
     * @return 结果
     */
    IPage<RoleMenuLinkVo> getPage(RoleMenuLinkDto dto);

    /**
     * 查询角色、菜单关系
     *
     * @param dto 角色、菜单关系
     * @return 结果
     */
    RoleMenuLinkVo getOne(RoleMenuLinkDto dto);
}
