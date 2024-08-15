package org.example.springboot.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 密码信息
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "密码信息实体", description = "密码信息")
public class PasswordDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -9053124098866924237L;
    /**
     * 原密码
     */
    @Schema(description = "原密码")
    @NotBlank(message = "{password.password.NotBlank}")
    private String password;
    /**
     * 新密码
     */
    @Schema(description = "新密码")
    @NotBlank(message = "{password.newPassword.NotBlank}")
    private String newPassword;
    /**
     * 确认密码
     */
    @Schema(description = "确认密码")
    @NotBlank(message = "{password.confirmPassword.NotBlank}")
    private String confirmPassword;
}
