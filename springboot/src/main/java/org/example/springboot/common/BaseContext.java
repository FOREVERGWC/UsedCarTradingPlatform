package org.example.springboot.common;

import org.example.springboot.domain.vo.UserVo;

public class BaseContext {
    private static final ThreadLocal<UserVo> threadLocal = new ThreadLocal<>();

    /**
     * 获取用户
     *
     * @return 当前用户
     */
    public static UserVo getUser() {
        return threadLocal.get();
    }

    /**
     * 设置用户
     *
     * @param user 当前用户
     */
    public static void setUser(UserVo user) {
        threadLocal.set(user);
    }
}
