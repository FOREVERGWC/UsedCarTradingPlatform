package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.ChatDto;
import org.example.springboot.domain.entity.Chat;
import org.example.springboot.domain.vo.ChatVo;

import java.util.List;

/**
 * <p>
 * 聊天服务类
 * </p>
 */
public interface IChatService extends IService<Chat> {
    /**
     * 查询聊天列表
     *
     * @param dto 聊天
     * @return 结果
     */
    List<ChatVo> getList(ChatDto dto);

    /**
     * 查询聊天分页
     *
     * @param dto 聊天
     * @return 结果
     */
    IPage<ChatVo> getPage(ChatDto dto);

    /**
     * 查询聊天
     *
     * @param dto 聊天
     * @return 结果
     */
    ChatVo getOne(ChatDto dto);
}
