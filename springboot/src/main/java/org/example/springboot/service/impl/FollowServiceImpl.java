package org.example.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.common.BaseContext;
import org.example.springboot.domain.dto.FollowDto;
import org.example.springboot.domain.entity.Follow;
import org.example.springboot.domain.entity.User;
import org.example.springboot.domain.vo.FollowVo;
import org.example.springboot.domain.vo.UserVo;
import org.example.springboot.mapper.FollowMapper;
import org.example.springboot.service.IFollowService;
import org.example.springboot.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 关注服务实现类
 * </p>
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {
    @Resource
    private IUserService userService;

    @Override
    public boolean saveOrUpdate(Follow entity) {
        if (entity.getId() == null) {
            UserVo user = BaseContext.getUser();
            entity.setFollowerId(user.getId());
            return super.save(entity);
        }
        return super.saveOrUpdate(entity);
    }

    @Override
    public List<FollowVo> getList(FollowDto dto) {
        List<Follow> followList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(followList)) {
            return List.of();
        }
        // 关注者
        List<Long> followerIdList = followList.stream().map(Follow::getFollowerId).toList();
        List<User> followerList = userService.listByIds(followerIdList);
        Map<Long, User> followerMap = followerList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 被关注者
        List<Long> followedIdList = followList.stream().map(Follow::getFollowedId).toList();
        List<User> followedList = userService.listByIds(followedIdList);
        Map<Long, User> followedMap = followedList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return followList.stream().map(item -> {
            FollowVo vo = new FollowVo();
            BeanUtils.copyProperties(item, vo);
            vo.setFollower(followerMap.getOrDefault(item.getFollowerId(), User.builder().name("已删除").build()));
            vo.setFollowed(followedMap.getOrDefault(item.getFollowedId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<FollowVo> getPage(FollowDto dto) {
        Page<Follow> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 关注者
        List<Long> followerIdList = info.getRecords().stream().map(Follow::getFollowerId).toList();
        List<User> followerList = userService.listByIds(followerIdList);
        Map<Long, User> followerMap = followerList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 被关注者
        List<Long> followedIdList = info.getRecords().stream().map(Follow::getFollowedId).toList();
        List<User> followedList = userService.listByIds(followedIdList);
        Map<Long, User> followedMap = followedList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            FollowVo vo = new FollowVo();
            BeanUtils.copyProperties(item, vo);
            vo.setFollower(followerMap.getOrDefault(item.getFollowerId(), User.builder().name("已删除").build()));
            vo.setFollowed(followedMap.getOrDefault(item.getFollowedId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public FollowVo getOne(FollowDto dto) {
        Follow one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 关注者
        User follower = Optional.ofNullable(userService.getById(one.getFollowerId())).orElse(User.builder().name("已删除").build());
        // 被关注者
        User followed = Optional.ofNullable(userService.getById(one.getFollowedId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        FollowVo vo = new FollowVo();
        BeanUtils.copyProperties(one, vo);
        vo.setFollower(follower);
        vo.setFollowed(followed);
        return vo;
    }

    @Override
    public Map<String, Object> getInfo(Long userId) {
        Long id = Optional.ofNullable(BaseContext.getUser()).orElse(UserVo.builder().build()).getId();
        long followerCount = count(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowedId, userId));
        long followedCount = count(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId, userId));
        boolean isFollowing = id != null && lambdaQuery()
                .eq(Follow::getFollowerId, id)
                .eq(Follow::getFollowedId, userId)
                .exists();
        return Map.of("followerCount", followerCount, "followedCount", followedCount, "isFollowing", isFollowing);
    }

    @Override
    public Map<String, Object> followTo(Long userId) {
        UserVo user = BaseContext.getUser();
        if (Objects.equals(user.getId(), userId)) {
            throw new RuntimeException("操作失败！不能对自己进行此操作");
        }
        Follow follow = Follow.builder()
                .followerId(user.getId())
                .followedId(userId)
                .build();
        Follow one = getWrapper(follow).one();
        if (one != null) {
            removeById(one);
        } else {
            save(follow);
        }
        return getInfo(userId);
    }

    /**
     * 组装查询包装器
     *
     * @param entity 关注
     * @return 结果
     */
    private LambdaQueryChainWrapper<Follow> getWrapper(Follow entity) {
        LambdaQueryChainWrapper<Follow> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Follow::getId, entity.getId())
                .eq(entity.getFollowerId() != null, Follow::getFollowerId, entity.getFollowerId())
                .eq(entity.getFollowedId() != null, Follow::getFollowedId, entity.getFollowedId());
        if (entity instanceof FollowDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    Follow::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
