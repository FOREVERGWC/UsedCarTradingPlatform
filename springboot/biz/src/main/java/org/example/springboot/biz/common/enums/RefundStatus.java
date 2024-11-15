package org.example.springboot.biz.common.enums;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 退款状态
 */
@Getter
@AllArgsConstructor
public enum RefundStatus {
    /**
     * 未退款
     */
    NO_REFUND(0, "未退款"),
    /**
     * 已退款
     */
    HAS_REFUND(1, "已退款");

    private static final Map<Integer, RefundStatus> map = new HashMap<>();

    static {
        for (RefundStatus item : RefundStatus.values()) {
            map.put(item.getCode(), item);
        }
    }

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String msg;

    @JsonCreator
    private static RefundStatus jacksonInstance(final JsonNode jsonNode) {
        Integer code = Convert.toInt(jsonNode.asText());
        return map.get(code);
    }

    /**
     * 根据键获取枚举
     *
     * @param code 键
     * @return 结果
     */
    public static RefundStatus getByCode(Integer code) {
        return map.get(code);
    }
}
