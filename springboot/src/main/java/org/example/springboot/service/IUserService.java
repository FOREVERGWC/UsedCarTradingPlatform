package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.UserDto;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.model.RegisterBody;
import org.example.springboot.domain.model.ResetBody;
import org.example.springboot.domain.vo.UserVo;

import java.util.List;

/**
 * <p>
 * 用户信息服务类
 * </p>
 */
public interface IUserService extends IService<User> {
    /**
     * 添加或修改用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    boolean saveOrUpdate(UserDto dto);

    /**
     * 查询用户信息列表
     *
     * @param dto 用户信息
     * @return 结果
     */
    List<UserVo> getList(UserDto dto);

    /**
     * 查询用户信息分页
     *
     * @param dto 用户信息
     * @return 结果
     */
    IPage<UserVo> getPage(UserDto dto);

    /**
     * 查询用户信息
     *
     * @param dto 用户信息
     * @return 结果
     */
    UserVo getOne(UserDto dto);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    User getByUsername(String username);

    /**
     * 根据电话查询用户信息
     *
     * @param phone 电话
     * @return 结果
     */
    User getByPhone(String phone);

    /**
     * 根据邮箱查询用户信息
     *
     * @param email 邮箱
     * @return 结果
     */
    User getByEmail(String email);

    /**
     * 解禁或禁用用户
     *
     * @param id 用户ID
     */
    void handleStatus(Long id);

    /**
     * PC端登录
     *
     * @param loginBody 端登录请求体
     * @return 结果
     */
    UserVo login(LoginBody loginBody);

    /**
     * 注册用户
     *
     * @param body PC端注册请求体
     */
    void register(RegisterBody body);

    /**
     * 重置密码
     *
     * @param body 密码信息
     */
    void resetPassword(ResetBody body);
}
