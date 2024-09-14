package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.domain.dto.ChatDto;
import org.example.springboot.domain.entity.Chat;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.vo.ChatVo;
import org.example.springboot.mapper.ChatMapper;
import org.example.springboot.service.IChatService;
import org.example.springboot.service.IUserService;
import org.example.springboot.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 聊天服务实现类
 * </p>
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {
    @Resource
    private IUserService userService;

    @Override
    public boolean save(Chat entity) {
        Long userId = UserUtils.getLoginUserId();
        entity.setSenderId(userId);
        return super.save(entity);
    }

    @Override
    public boolean saveOrUpdate(Chat entity) {
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public List<ChatVo> getList(ChatDto dto) {
        List<Chat> chatList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(chatList)) {
            return List.of();
        }
        // 发送者
        List<Long> senderIdList = chatList.stream().map(Chat::getSenderId).toList();
        List<User> senderList = userService.listByIds(senderIdList);
        Map<Long, User> senderMap = senderList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 接收者
        List<Long> receiverIdList = chatList.stream().map(Chat::getReceiverId).toList();
        List<User> receiverList = userService.listByIds(receiverIdList);
        Map<Long, User> receiverMap = receiverList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return chatList.stream().map(item -> {
            ChatVo vo = new ChatVo();
            BeanUtils.copyProperties(item, vo);
            vo.setSender(senderMap.getOrDefault(item.getSenderId(), User.builder().name("已删除").build()));
            vo.setReceiver(receiverMap.getOrDefault(item.getReceiverId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<ChatVo> getPage(ChatDto dto) {
        Page<Chat> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 发送者
        List<Long> senderIdList = info.getRecords().stream().map(Chat::getSenderId).toList();
        List<User> senderList = userService.listByIds(senderIdList);
        Map<Long, User> senderMap = senderList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 接收者
        List<Long> receiverIdList = info.getRecords().stream().map(Chat::getReceiverId).toList();
        List<User> receiverList = userService.listByIds(receiverIdList);
        Map<Long, User> receiverMap = receiverList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            ChatVo vo = new ChatVo();
            BeanUtils.copyProperties(item, vo);
            vo.setSender(senderMap.getOrDefault(item.getSenderId(), User.builder().name("已删除").build()));
            vo.setReceiver(receiverMap.getOrDefault(item.getReceiverId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public ChatVo getOne(ChatDto dto) {
        Chat one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 发送者
        User sender = Optional.ofNullable(userService.getById(one.getSenderId())).orElse(User.builder().name("已删除").build());
        // 接收者
        User receiver = Optional.ofNullable(userService.getById(one.getReceiverId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        ChatVo vo = new ChatVo();
        BeanUtils.copyProperties(one, vo);
        vo.setSender(sender);
        vo.setReceiver(receiver);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 聊天
     * @return 结果
     */
    private LambdaQueryChainWrapper<Chat> getWrapper(Chat entity) {
        LambdaQueryChainWrapper<Chat> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Chat::getId, entity.getId())
                .eq(entity.getSenderId() != null, Chat::getSenderId, entity.getSenderId())
                .eq(entity.getReceiverId() != null, Chat::getReceiverId, entity.getReceiverId())
                .like(StrUtil.isNotBlank(entity.getMessage()), Chat::getMessage, entity.getMessage());
        if (entity instanceof ChatDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    Chat::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
