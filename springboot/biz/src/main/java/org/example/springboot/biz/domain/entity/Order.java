package org.example.springboot.biz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.example.springboot.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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
@TableName("biz_order")
@Schema(name = "订单实体", description = "订单")
public class Order extends BaseEntity {
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
     * 审核ID
     */
    @Schema(description = "审核ID")
    private Long carAuditeId;
    /**
     * 卖方ID
     */
    @Schema(description = "卖方ID")
    private Long sellId;
    /**
     * 买方ID
     */
    @Schema(description = "买方ID")
    private Long buyId;
    /**
     * 送货地址ID
     */
    @Schema(description = "送货地址ID")
    private Long addressId;
    /**
     * 付款时间
     */
    @Schema(description = "付款时间")
    private LocalDateTime payTime;
    /**
     * 付款金额
     */
    @Schema(description = "付款金额")
    private BigDecimal payPrice;
    /**
     * 付款状态(0未付款、1已付款)
     */
    @Schema(description = "付款状态(0未付款、1已付款)")
    private Byte payStatus;
    /**
     * 退款时间
     */
    @Schema(description = "退款时间")
    private LocalDateTime refundTime;
    /**
     * 退款原因
     */
    @Schema(description = "退款原因")
    private String refundReason;
    /**
     * 退款状态(0未退款、1已退款)
     */
    @Schema(description = "退款状态(0未退款、1已退款)")
    private Byte refundStatus;
}
