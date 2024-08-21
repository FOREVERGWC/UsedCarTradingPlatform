package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.UserDto;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.model.LoginBody;
import org.example.springboot.domain.dto.PasswordDto;
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
     * PC端登录
     *
     * @param loginBody 端登录请求体
     * @return 结果
     */
    UserVo login(LoginBody loginBody);

    /**
     * 注册用户
     *
     * @param user 用户信息
     */
    void register(User user);

    /**
     * 修改密码
     *
     * @param dto 修改密码
     */
    void updatePassword(PasswordDto dto);
}
