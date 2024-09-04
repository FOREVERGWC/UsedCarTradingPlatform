package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.RoleDto;
import org.example.springboot.domain.entity.Role;
import org.example.springboot.domain.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色服务类
 * </p>
 */
public interface IRoleService extends IService<Role> {
    /**
     * 根据用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<Role> listByUserId(Long userId);

//    /**
//     * 根据用户ID查询角色ID列表
//     *
//     * @param userId 用户ID
//     * @return 结果
//     */
//    List<Long> listRoleIdsByUserId(Long userId);

    /**
     * 根据用户ID列表批量查询角色ID集合
     *
     * @param userIds 用户ID列表
     * @return 结果
     */
    Map<Long, List<Long>> mapRoleIdsByUserIds(List<Long> userIds);

    /**
     * 根据用户ID列表批量查询角色集合
     *
     * @param userIds 用户ID列表
     * @return 结果
     */
    Map<Long, List<Role>> mapByUserIds(List<Long> userIds);

    /**
     * 查询角色列表
     *
     * @param dto 角色
     * @return 结果
     */
    List<RoleVo> getList(RoleDto dto);

    /**
     * 查询角色分页
     *
     * @param dto 角色
     * @return 结果
     */
    IPage<RoleVo> getPage(RoleDto dto);

    /**
     * 查询角色
     *
     * @param dto 角色
     * @return 结果
     */
    RoleVo getOne(RoleDto dto);

    /**
     * 恢复或停用角色
     *
     * @param id 角色ID
     */
    void handleStatus(Long id);
}
