package org.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.domain.dto.FollowDto;
import org.example.springboot.domain.entity.Follow;
import org.example.springboot.domain.vo.FollowVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关注服务类
 * </p>
 */
public interface IFollowService extends IService<Follow> {
    /**
     * 查询关注列表
     *
     * @param dto 关注
     * @return 结果
     */
    List<FollowVo> getList(FollowDto dto);

    /**
     * 查询关注分页
     *
     * @param dto 关注
     * @return 结果
     */
    IPage<FollowVo> getPage(FollowDto dto);

    /**
     * 查询关注
     *
     * @param dto 关注
     * @return 结果
     */
    FollowVo getOne(FollowDto dto);

    /**
     * 查询粉丝数量和关注数量
     *
     * @param userId 用户ID
     * @return 结果
     */
    Map<String, Object> getInfo(Long userId);

    /**
     * 关注或取关用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    Map<String, Object> followTo(Long userId);
}
