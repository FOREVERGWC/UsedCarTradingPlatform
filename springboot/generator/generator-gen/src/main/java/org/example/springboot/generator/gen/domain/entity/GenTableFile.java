package org.example.springboot.generator.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.common.domain.BaseEntity;

import java.io.Serial;

/**
 * <p>
 * 代码生成业务文件
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table_file")
@Schema(name = "代码生成业务文件实体", description = "代码生成业务文件")
public class GenTableFile extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 8679071412928818866L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 归属表ID
     */
    @Schema(description = "归属表ID")
    private Long tableId;
    /**
     * 实体类名称
     */
    @Schema(description = "实体类名称")
    private String entityName;
    /**
     * DTO名称
     */
    @Schema(description = "DTO类名称")
    private String dtoName;
    /**
     * VO名称
     */
    @Schema(description = "VO类名称")
    private String voName;
    /**
     * 映射名称
     */
    @Schema(description = "映射类名称")
    private String mapperName;
    /**
     * 服务类名称
     */
    @Schema(description = "服务类名称")
    private String serviceName;
    /**
     * 服务实现类名称
     */
    @Schema(description = "服务实现类名称")
    private String implName;
    /**
     * 控制器名称
     */
    @Schema(description = "控制器类名称")
    private String controllerName;
    /**
     * API文件名称
     */
    @Schema(description = "API文件名称")
    private String apiName;
}
