package org.example.springboot.service.impl.cache;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ArticleCacheService implements IArticleCacheService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addViewCount(Long id) {
        String key = "article:view:" + id;
        redisTemplate.opsForValue().increment(key);
    }

    @Override
    public Long getViewCount(Long id) {
        String key = "article:view:" + id;
        Object value = redisTemplate.opsForValue().get(key);
        return Convert.toLong(value, 0L);
    }

    @Override
    public Long getAllViewCount() {
        String pattern = "article:view:*";
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollectionUtil.isEmpty(keys)) {
            return 0L;
        }
        return keys.stream()
                .map(key -> Convert.toLong(redisTemplate.opsForValue().get(key), 0L))
                .reduce(0L, Long::sum);
    }

    @Override
    public void addUserHistory(Long userId, Long id) {
        String key = "user:" + userId + ":article:" + id;
        String value = DateUtil.today();
        double score = Instant.now().toEpochMilli();
        redisTemplate.opsForZSet().add(key, value, score);
        redisTemplate.expire(key, 30, TimeUnit.DAYS);
    }

    @Override
    public List<Map<Object, Object>> getUserHistory(Long userId, Integer pageNo, Integer pageSize) {
        String pattern = "user:" + userId + ":article:*";
        Set<String> keys = redisTemplate.keys(pattern);
        int start = (pageNo - 1) * pageSize;

        if (CollectionUtil.isEmpty(keys) || start >= keys.size()) {
            return new LinkedList<>();
        }

        return keys.stream()
                .flatMap(key -> Optional.ofNullable(redisTemplate
                                .opsForZSet()
                                .rangeByScoreWithScores(key, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
                        .orElse(new HashSet<>())
                        .stream()
                        .map(tuple -> {
                            String articleId = key.split(":")[3];
                            Map<Object, Object> map = new HashMap<>();
                            map.put("userId", userId);
                            map.put("articleId", articleId);
                            map.put("time", new Date(Convert.toLong(tuple.getScore())));
                            return map;
                        })
                )
                .sorted((a, b) -> ((Date) b.get("time")).compareTo((Date) a.get("time")))
                .skip(start)
                .limit(pageSize)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
