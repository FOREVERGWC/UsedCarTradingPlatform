package org.example.springboot.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.common.annotation.Dict;
import org.example.springboot.common.enums.Gender;
import org.example.springboot.common.enums.UserStatus;
import org.example.springboot.domain.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@Schema(name = "用户信息实体", description = "用户信息")
public class User extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;
    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;
    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;
    /**
     * 性别(0女、1男、2未知)
     */
    @Dict(enumClass = Gender.class)
    @Schema(description = "性别(0女、1男、2未知)")
    private String gender;
    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDateTime birthday;
    /**
     * 状态(0禁用、1正常)
     */
    @Dict(enumClass = UserStatus.class)
    @Schema(description = "状态(0禁用、1正常)")
    private String status;
    /**
     * 电话
     */
    @Schema(description = "电话")
    private String phone;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 微信小程序开放ID
     */
    @Schema(description = "微信小程序开放ID")
    private String openId;
    /**
     * 余额
     */
    @Schema(description = "余额")
    private BigDecimal balance;
    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime loginTime;
    /**
     * 令牌
     */
    @Schema(description = "令牌")
    @TableField(exist = false)
    private String token;
}
