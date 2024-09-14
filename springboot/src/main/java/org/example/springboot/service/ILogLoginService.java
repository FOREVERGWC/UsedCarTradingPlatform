package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.LogLoginDto;
import org.example.springboot.domain.entity.system.LogLogin;
import org.example.springboot.domain.vo.LogLoginVo;

import java.util.List;

/**
 * <p>
 * 登录日志服务类
 * </p>
 */
public interface ILogLoginService extends IService<LogLogin> {
    /**
     * 记录登录日志
     *
     * @param userId   用户ID
     * @param username 用户名
     * @param status   状态
     * @param msg      消息
     */
    void record(Long userId, String username, Boolean status, String msg);

    /**
     * 查询登录日志列表
     *
     * @param dto 登录日志
     * @return 结果
     */
    List<LogLoginVo> getList(LogLoginDto dto);

    /**
     * 查询登录日志分页
     *
     * @param dto 登录日志
     * @return 结果
     */
    IPage<LogLoginVo> getPage(LogLoginDto dto);

    /**
     * 查询登录日志
     *
     * @param dto 登录日志
     * @return 结果
     */
    LogLoginVo getOne(LogLoginDto dto);
}
