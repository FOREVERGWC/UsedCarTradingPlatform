package org.example.springboot.biz.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.system.domain.entity.User;

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
@Schema(name = "二手车实体", description = "二手车")
public class CarVo extends Car {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    @Schema(description = "用户")
    private User user;
    /**
     * 燃料类型
     */
    @Schema(description = "燃料类型")
    private String fuelTypeText;
    /**
     * 变速器类型
     */
    @Schema(description = "变速器类型")
    private String transmissionTypeText;
    /**
     * 车况
     */
    @Schema(description = "车况")
    private String conditionText;
}
