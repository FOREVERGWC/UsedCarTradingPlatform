package org.example.springboot.service.impl.cache;

import java.util.List;
import java.util.Map;

public interface IArticleCacheService {

    /**
     * 增加文章浏览量
     *
     * @param id 文章ID
     */
    void addViewCount(Long id);

    /**
     * 获取文章浏览量
     *
     * @param id 文章ID
     * @return 浏览量
     */
    Long getViewCount(Long id);

    /**
     * 记录用户的文章浏览历史
     *
     * @param userId 用户ID
     * @param id     文章ID
     */
    void addUserHistory(Long userId, Long id);

    /**
     * 分页获取用户的文章浏览历史
     *
     * @param userId   用户ID
     * @param pageNo   页码
     * @param pageSize 每页大小
     * @return 浏览历史列表
     */
    // TODO 封装dto和vo
    List<Map<Object, Object>> getUserHistory(Long userId, Integer pageNo, Integer pageSize);
}