package org.example.springboot.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
}
