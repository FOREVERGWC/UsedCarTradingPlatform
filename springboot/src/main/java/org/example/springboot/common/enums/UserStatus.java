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
 * 用户状态
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
    /**
     * 禁用
     */
    DISABLE("0", "禁用"),
    /**
     * 正常
     */
    NORMAL("1", "正常");

    private static final Map<String, UserStatus> map = new HashMap<>();

    static {
        for (UserStatus item : UserStatus.values()) {
            map.put(item.getCode(), item);
        }
    }

    @EnumValue
    private final String code;
    @JsonValue
    private final String msg;

    @JsonCreator
    private static UserStatus jacksonInstance(final JsonNode jsonNode) {
        String code = jsonNode.asText();
        return map.get(code);
    }
}
