package org.example.springboot.service.cache;

public interface ICaptchaService {
    /**
     * 设置验证码
     *
     * @param uuid  唯一标识
     * @param value 验证码
     */
    void setCaptcha(String uuid, String value);

    /**
     * 获取验证码
     *
     * @param uuid 唯一标识
     * @return 结果
     */
    String getCaptcha(String uuid);

    /**
     * 销毁验证码
     *
     * @param uuid 唯一标识
     */
    void removeCaptcha(String uuid);

    /**
     * 校验验证码
     *
     * @param uuid 唯一标识
     * @param code 验证码
     */
    void validateLoginCode(String uuid, String code);

    /**
     * 校验验证码
     *
     * @param uuid 唯一标识
     * @param code 验证码
     */
    void validateCode(String uuid, String code);
}
