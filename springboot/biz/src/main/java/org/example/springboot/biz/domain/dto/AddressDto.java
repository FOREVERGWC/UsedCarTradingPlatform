package org.example.springboot.biz.domain.dto;

import org.example.springboot.biz.domain.entity.Address;
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
 * 用户地址
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "用户地址实体", description = "用户地址")
public class AddressDto extends Address {
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