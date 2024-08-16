package org.example.springboot.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.domain.entity.User;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "用户信息实体", description = "用户信息")
public class UserVo extends User {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 令牌
     */
    @Schema(description = "令牌")
    @TableField(exist = false)
    private String token;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private String statusText;
    /**
     * 角色
     */
    @Schema(description = "角色")
    private String roleText;
}
