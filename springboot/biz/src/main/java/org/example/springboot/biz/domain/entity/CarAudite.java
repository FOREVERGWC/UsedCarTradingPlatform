package org.example.springboot.biz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.springboot.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 二手车审核
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("biz_car_audite")
@Schema(name = "二手车审核实体", description = "二手车审核")
public class CarAudite extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 车辆ID
     */
    @Schema(description = "车辆ID")
    private Long carId;
    /**
     * 卖方ID
     */
    @Schema(description = "卖方ID")
    private Long userId;
    /**
     * 审核员ID
     */
    @Schema(description = "审核员ID")
    private Long auditorId;
    /**
     * 配置
     */
    @Schema(description = "配置")
    private String info;
}
