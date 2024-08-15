package org.example.springboot.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章可见性
 */
@Getter
@AllArgsConstructor
public enum ArticleVisible {
    /**
     * 私有
     */
    PRIVATE("0", "私有"),
    /**
     * 公开
     */
    PUBLIC("1", "公开");

    private static final Map<String, ArticleVisible> map = new HashMap<>();

    static {
        for (ArticleVisible item : ArticleVisible.values()) {
            map.put(item.getCode(), item);
        }
    }

    @EnumValue
    private final String code;
    @JsonValue
    private final String msg;

    @JsonCreator
    private static ArticleVisible jacksonInstance(final JsonNode jsonNode) {
        String code = jsonNode.asText();
        return map.get(code);
    }

    /**
     * 根据键获取枚举
     *
     * @param code 键
     * @return 结果
     */
    public static ArticleVisible getByCode(String code) {
        return map.get(code);
    }
}
