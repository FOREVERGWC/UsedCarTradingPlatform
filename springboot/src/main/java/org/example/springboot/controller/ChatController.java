package org.example.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.springboot.domain.vo.ChatVo;
import org.example.springboot.domain.Result;
import org.example.springboot.domain.entity.Chat;
import org.example.springboot.domain.dto.ChatDto;
import org.example.springboot.service.IChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 聊天前端控制器
 * </p>
 */
@RestController
@RequestMapping("/chat")
@Tag(name = "聊天", description = "聊天")
public class ChatController {
    @Resource
    private IChatService chatService;

    /**
     * 添加、修改聊天
     *
     * @param chat 聊天
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改聊天", description = "添加、修改聊天", method = "POST")
    public Result<Void> save(@RequestBody Chat chat) {
        chatService.saveOrUpdate(chat);
        return Result.success();
    }

    /**
     * 删除聊天
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除聊天", description = "删除聊天", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        chatService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询聊天列表
     *
     * @param dto 聊天
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询聊天列表", description = "查询聊天列表", method = "GET")
    public Result<List<ChatVo>> getList(ChatDto dto) {
        List<ChatVo> list = chatService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询聊天分页
     *
     * @param dto 聊天
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询聊天分页", description = "查询聊天分页", method = "GET")
    public Result<IPage<ChatVo>> getPage(ChatDto dto) {
        IPage<ChatVo> page = chatService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询聊天
     *
     * @param dto 聊天
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询聊天", description = "查询聊天", method = "GET")
    public Result<ChatVo> getOne(ChatDto dto) {
        ChatVo vo = chatService.getOne(dto);
        return Result.success(vo);
    }
}
