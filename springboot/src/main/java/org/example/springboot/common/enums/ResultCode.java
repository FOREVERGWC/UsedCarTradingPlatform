package org.example.springboot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息响应码
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！"),
    /**
     * 参数异常
     */
    PARAM_ERROR(400, "参数异常"),
    /**
     * 无效的token
     */
    TOKEN_INVALID_ERROR(401, "无效的token"),
    /**
     * token验证失败，请重新登录
     */
    TOKEN_CHECK_ERROR(401, "token验证失败，请重新登录"),
    /**
     * 参数缺失
     */
    PARAM_LOST_ERROR(4001, "参数缺失"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常"),
    /**
     * 操作失败！用户不存在
     */
    USER_NOT_FOUND_ERROR(1000, "操作失败！用户不存在"),
    /**
     * 用户名已存在
     */
    USER_EXIST_ERROR(1001, "用户名已存在"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(1002, "用户未登录"),
    /**
     * 账号或密码错误
     */
    USER_ACCOUNT_ERROR(1003, "账号或密码错误"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST_ERROR(1004, "用户不存在"),
    /**
     * 原密码输入错误
     */
    PARAM_PASSWORD_ERROR(1005, "原密码输入错误"),
    /**
     * 账户余额不足，请到个人中心充值
     */
    ACCOUNT_LOW_ERROR(1006, "账户余额不足，请到个人中心充值"),
    /**
     * 删除失败！该角色已分配用户
     */
    ROLE_DELETE_ERROR(2000, "删除失败！该角色已分配用户"),
    /**
     * 操作失败！文章不存在
     */
    ARTICLE_NOT_FOUND_ERROR(6000, "操作失败！文章不存在");

    private final Integer code;
    private final String msg;
}
