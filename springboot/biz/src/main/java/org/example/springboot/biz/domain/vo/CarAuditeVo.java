package org.example.springboot.biz.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.biz.domain.entity.CarAudite;
import org.example.springboot.system.domain.entity.User;

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
@Schema(name = "二手车审核实体", description = "二手车审核")
public class CarAuditeVo extends CarAudite {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 车辆
     */
    @Schema(description = "车辆")
    private Car car;
    /**
     * 卖方
     */
    @Schema(description = "卖方")
    private User user;
    /**
     * 审核员
     */
    @Schema(description = "审核员")
    private User auditor;
}
