package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.common.BaseContext;
import org.example.springboot.domain.dto.CommentDto;
import org.example.springboot.domain.entity.Comment;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.vo.CommentVo;
import org.example.springboot.domain.vo.UserVo;
import org.example.springboot.mapper.CommentMapper;
import org.example.springboot.service.ICommentService;
import org.example.springboot.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论服务实现类
 * </p>
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Resource
    private IUserService userService;

    @Override
    public boolean saveOrUpdate(Comment entity) {
        if (entity.getId() == null) {
            UserVo user = BaseContext.getUser();
            entity.setUserId(user.getId());
            return super.save(entity);
        }
        return super.saveOrUpdate(entity);
    }

    @Override
    public List<CommentVo> getList(CommentDto dto) {
        List<Comment> commentList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(commentList)) {
            return List.of();
        }
        // 回复
        List<Long> replyIdList = commentList.stream().map(Comment::getReplyId).toList();
//        List<CommentVo> replyList = listByIds(replyIdList);
        List<CommentVo> replyList = List.of();
        Map<Long, CommentVo> replyMap = replyList.stream().collect(Collectors.toMap(CommentVo::getId, item -> item));
        // 用户
        List<Long> userIdList = commentList.stream().map(Comment::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return commentList.stream().map(item -> {
            CommentVo vo = new CommentVo();
            BeanUtils.copyProperties(item, vo);
            vo.setReply(replyMap.getOrDefault(item.getReplyId(), CommentVo.builder().content("已删除").build()));
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<CommentVo> getPage(CommentDto dto) {
        Page<Comment> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 回复
        List<Long> replyIdList = info.getRecords().stream().map(Comment::getReplyId).toList();
//        List<CommentVo> replyList = listByIds(replyIdList);
        List<CommentVo> replyList = List.of();
        Map<Long, CommentVo> replyMap = replyList.stream().collect(Collectors.toMap(CommentVo::getId, item -> item));
        // 用户
        List<Long> userIdList = info.getRecords().stream().map(Comment::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            CommentVo vo = new CommentVo();
            BeanUtils.copyProperties(item, vo);
            vo.setReply(replyMap.getOrDefault(item.getReplyId(), CommentVo.builder().content("已删除").build()));
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public CommentVo getOne(CommentDto dto) {
        Comment one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 回复
//        CommentVo reply = Optional.ofNullable(getById(one.getReplyId())).orElse(CommentVo.builder().content("已删除").build());
        CommentVo reply = CommentVo.builder().content("已删除").build();
        // 用户
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        CommentVo vo = new CommentVo();
        BeanUtils.copyProperties(one, vo);
        vo.setReply(reply);
        vo.setUser(user);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param dto 评论
     * @return 结果
     */
    private LambdaQueryChainWrapper<Comment> getWrapper(CommentDto dto) {
        Map<String, Object> params = dto.getParams();
        // 创建时间
        Object startCreateTime = params == null ? null : params.get("startCreateTime");
        Object endCreateTime = params == null ? null : params.get("endCreateTime");
        return lambdaQuery()
                .eq(dto.getId() != null, Comment::getId, dto.getId())
                .eq(dto.getBizId() != null, Comment::getBizId, dto.getBizId())
                .like(StrUtil.isNotBlank(dto.getBizKey()), Comment::getBizKey, dto.getBizKey())
                .like(StrUtil.isNotBlank(dto.getContent()), Comment::getContent, dto.getContent())
                .eq(dto.getReplyId() != null, Comment::getReplyId, dto.getReplyId())
                .eq(dto.getUserId() != null, Comment::getUserId, dto.getUserId())
                .like(StrUtil.isNotBlank(dto.getOs()), Comment::getOs, dto.getOs())
                .like(StrUtil.isNotBlank(dto.getIp()), Comment::getIp, dto.getIp())
                .like(StrUtil.isNotBlank(dto.getLocation()), Comment::getLocation, dto.getLocation())
                .between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime), Comment::getCreateTime, startCreateTime, endCreateTime);
    }
}
