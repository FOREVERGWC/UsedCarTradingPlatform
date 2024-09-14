package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.example.springboot.domain.entity.system.LogLogin;
import org.example.springboot.domain.entity.system.User;
import org.example.springboot.domain.dto.LogLoginDto;
import org.example.springboot.domain.vo.LogLoginVo;
import org.example.springboot.mapper.LogLoginMapper;
import org.example.springboot.service.ILogLoginService;
import org.example.springboot.service.IUserService;
import jakarta.annotation.Resource;
import org.example.springboot.utils.AddressUtils;
import org.example.springboot.utils.ServletUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录日志服务实现类
 * </p>
 */
@Service
public class LogLoginServiceImpl extends ServiceImpl<LogLoginMapper, LogLogin> implements ILogLoginService {
    @Resource
    private IUserService userService;

    @Override
    public void record(Long userId, String username, Boolean status, String msg) {
        HttpServletRequest request = ServletUtils.getRequest();
        UserAgent ua = ServletUtils.getUserAgent(request);
        String ip = ServletUtils.getUserIp(request);
        String location = AddressUtils.getRealAddressByIP(ip);
        save(LogLogin.builder()
                .userId(userId)
                .os(ua.getOs().getName())
                .browser(ua.getBrowser().getName())
                .ip(ip)
                .location(location)
                .status(status)
                .msg(msg)
                .createBy(username)
                .updateBy(username)
                .build());
    }

    @Override
    public List<LogLoginVo> getList(LogLoginDto dto) {
        List<LogLogin> logLoginList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(logLoginList)) {
            return List.of();
        }
        // 用户
        List<Long> userIdList = logLoginList.stream().map(LogLogin::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return logLoginList.stream().map(item -> {
            LogLoginVo vo = new LogLoginVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<LogLoginVo> getPage(LogLoginDto dto) {
        Page<LogLogin> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 用户
        List<Long> userIdList = info.getRecords().stream().map(LogLogin::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            LogLoginVo vo = new LogLoginVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public LogLoginVo getOne(LogLoginDto dto) {
        LogLogin one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 用户
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        LogLoginVo vo = new LogLoginVo();
        BeanUtils.copyProperties(one, vo);
        vo.setUser(user);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 登录日志
     * @return 结果
     */
    private LambdaQueryChainWrapper<LogLogin> getWrapper(LogLogin entity) {
        LambdaQueryChainWrapper<LogLogin> wrapper = lambdaQuery()
                .eq(entity.getId() != null, LogLogin::getId, entity.getId())
                .eq(entity.getUserId() != null, LogLogin::getUserId, entity.getUserId())
                .like(StrUtil.isNotBlank(entity.getOs()), LogLogin::getOs, entity.getOs())
                .like(StrUtil.isNotBlank(entity.getBrowser()), LogLogin::getBrowser, entity.getBrowser())
                .like(StrUtil.isNotBlank(entity.getIp()), LogLogin::getIp, entity.getIp())
                .like(StrUtil.isNotBlank(entity.getLocation()), LogLogin::getLocation, entity.getLocation())
                .eq(entity.getStatus() != null, LogLogin::getStatus, entity.getStatus())
                .like(StrUtil.isNotBlank(entity.getMsg()), LogLogin::getMsg, entity.getMsg());
        if (entity instanceof LogLoginDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    LogLogin::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
