package org.example.springboot.domain.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.springboot.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 登录日志
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log_login")
@Schema(name = "登录日志实体", description = "登录日志")
public class LogLogin extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String os;
    /**
     * 浏览器
     */
    @Schema(description = "浏览器")
    private String browser;
    /**
     * IP
     */
    @Schema(description = "IP")
    private String ip;
    /**
     * IP属地
     */
    @Schema(description = "IP属地")
    private String location;
    /**
     * 状态(0失败、1成功)
     */
    @Schema(description = "状态(0失败、1成功)")
    private Boolean status;
    /**
     * 消息
     */
    @Schema(description = "消息")
    private String msg;
}
