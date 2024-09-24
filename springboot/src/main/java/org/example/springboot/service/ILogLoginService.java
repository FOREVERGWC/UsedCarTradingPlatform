package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
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

    /**
     * 导出登录日志
     *
     * @param logLogin 登录日志
     * @param response 响应对象
     */
    void exportExcel(LogLogin logLogin, HttpServletResponse response);
}
