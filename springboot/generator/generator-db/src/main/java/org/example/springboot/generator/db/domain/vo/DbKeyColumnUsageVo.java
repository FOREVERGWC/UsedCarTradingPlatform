package org.example.springboot.generator.db.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.generator.db.domain.entity.DbKeyColumnUsage;

import java.io.Serial;

/**
 * <p>
 * 键列使用情况
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "键列使用情况实体", description = "键列使用情况")
public class DbKeyColumnUsageVo extends DbKeyColumnUsage {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 占位符
     */
    @Schema(description = "占位符")
    private String placeholder;
}
