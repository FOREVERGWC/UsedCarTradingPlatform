package org.example.springboot.system.domain.dto;

import org.example.springboot.system.domain.entity.AttachmentChunk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.Map;

/**
 * <p>
 * 附件分片
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "附件分片实体", description = "附件分片")
public class AttachmentChunkDto extends AttachmentChunk {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 页码
     */
    @JsonIgnore
    private Integer pageNo;
    /**
     * 页面大小
     */
    @JsonIgnore
    private Integer pageSize;
    /**
     * 查询参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
}