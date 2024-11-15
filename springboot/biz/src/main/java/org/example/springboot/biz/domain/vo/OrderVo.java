package org.example.springboot.biz.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.biz.domain.entity.Address;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.biz.domain.entity.CarAudite;
import org.example.springboot.biz.domain.entity.Order;
import org.example.springboot.system.domain.entity.User;

import java.io.Serial;

/**
 * <p>
 * 订单
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "订单实体", description = "订单")
public class OrderVo extends Order {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 车辆
     */
    @Schema(description = "车辆")
    private Car car;
    /**
     * 审核
     */
    @Schema(description = "审核")
    private CarAudite carAudite;
    /**
     * 卖方
     */
    @Schema(description = "卖方")
    private User sell;
    /**
     * 买方
     */
    @Schema(description = "买方")
    private User buy;
    /**
     * 送货地址
     */
    @Schema(description = "送货地址")
    private Address address;
}
