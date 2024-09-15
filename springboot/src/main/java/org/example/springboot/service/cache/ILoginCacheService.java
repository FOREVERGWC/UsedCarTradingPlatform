package org.example.springboot.service.cache;

public interface ILoginCacheService {
    /**
     * 记录登录失败次数
     *
     * @param principal 用户身份
     */
    void addFailureCount(String principal);

    /**
     * 获取账户锁定状态
     *
     * @param principal 用户身份
     */
    boolean getAccountNonLocked(String principal);
}
