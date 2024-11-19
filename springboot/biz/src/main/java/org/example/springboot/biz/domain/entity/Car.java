package org.example.springboot.biz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.example.springboot.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 二手车
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("biz_car")
@Schema(name = "二手车实体", description = "二手车")
public class Car extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;
    /**
     * 品牌
     */
    @Schema(description = "品牌")
    private String brand;
    /**
     * 型号
     */
    @Schema(description = "型号")
    private String model;
    /**
     * 生产年份
     */
    @Schema(description = "生产年份")
    private Integer year;
    /**
     * 行驶里程
     */
    @Schema(description = "行驶里程")
    private Double mileage;
    /**
     * 价格
     */
    @Schema(description = "价格")
    private BigDecimal price;
    /**
     * 颜色
     */
    @Schema(description = "颜色")
    private String color;
    /**
     * 燃料类型(1汽油、2柴油、3电动、4混动、5其他)
     */
    @Schema(description = "燃料类型(1汽油、2柴油、3电动、4混动、5其他)")
    private String fuelType;
    /**
     * 变速器类型(1自动档、2手动档)
     */
    @Schema(description = "变速器类型(1自动档、2手动档)")
    private String transmissionType;
    /**
     * 车况(1九成新女生自用、2良好、3完好、4轻微刮擦、5叙利亚成色)
     */
    @Schema(description = "车况(1九成新女生自用、2良好、3完好、4轻微刮擦、5叙利亚成色)")
    @TableField(value = "`condition`")
    private String condition;
    /**
     * 上牌日期
     */
    @Schema(description = "上牌日期")
    private LocalDate licenseDate;
    /**
     * 是否售出
     */
    @Schema(description = "是否售出")
    private Boolean hasSold;
    /**
     * 是否验车
     */
    @Schema(description = "是否验车")
    private Boolean hasCheck;
}
