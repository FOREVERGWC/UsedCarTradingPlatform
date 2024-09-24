package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.domain.dto.MenuDto;
import org.example.springboot.domain.entity.system.Menu;
import org.example.springboot.domain.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单服务类
 * </p>
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据用户ID查询菜单列表
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<Menu> listByUserId(Long userId);

    /**
     * 查询菜单列表
     *
     * @param dto 菜单
     * @return 结果
     */
    List<MenuVo> getList(MenuDto dto);

    /**
     * 查询菜单树
     *
     * @param dto 菜单
     * @return 结果
     */
    List<MenuVo> getTree(MenuDto dto);

    /**
     * 查询用户菜单树
     *
     * @return 结果
     */
    List<MenuVo> getAuthTree();

    /**
     * 查询角色菜单树
     *
     * @param roleId 角色ID
     * @return 结果
     */
    List<MenuVo> getRoleTree(Long roleId);

    /**
     * 查询菜单分页
     *
     * @param dto 菜单
     * @return 结果
     */
    IPage<MenuVo> getPage(MenuDto dto);

    /**
     * 查询菜单
     *
     * @param dto 菜单
     * @return 结果
     */
    MenuVo getOne(MenuDto dto);

    /**
     * 导出菜单
     *
     * @param menu     菜单
     * @param response 响应对象
     */
    void exportExcel(Menu menu, HttpServletResponse response);

    /**
     * 恢复或停用菜单
     *
     * @param id 菜单ID
     */
    void handleStatus(Long id);

    /**
     * 显示或隐藏菜单
     *
     * @param id 菜单ID
     */
    void handleVisible(Long id);
}
