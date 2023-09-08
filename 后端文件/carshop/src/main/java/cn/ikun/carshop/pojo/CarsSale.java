package cn.ikun.carshop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 售卖车量的pojo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("cars_sale")
public class CarsSale {

    @TableId
    private Long vehicleId;//车量ID

    private String brand;//车量品牌

    private String model;//车量型号

    private Integer year;//制造年份

    private BigDecimal mileage;//已经行驶公里数

    private String img;//车量图片

    private String description;//具体描述

    private BigDecimal price;//价格

    @TableField(fill = FieldFill.INSERT)
    private int status;//车辆 状态0为正常 1为售出 2为下架

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间

    @TableField(fill = FieldFill.INSERT)
    private Long createAdmins;//创建的管理员ID

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateAdmins;//更新的管理员ID

    @TableLogic
    private Integer isDelete;//逻辑删除

}
