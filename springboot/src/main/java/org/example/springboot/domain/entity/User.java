package org.example.springboot.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import org.example.springboot.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

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
     * 生日
     */
    @Schema(description = "生日")
    private LocalDateTime birthday;
    /**
     * 状态(0禁用、1正常)
     */
    @Schema(description = "状态(0禁用、1正常)")
    private String status;
    /**
     * 角色(0管理员、1用户)
     */
    @Schema(description = "角色(0管理员、1用户)")
    private String role;
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
