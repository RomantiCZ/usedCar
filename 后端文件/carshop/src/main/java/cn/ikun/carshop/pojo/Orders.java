package cn.ikun.carshop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("orders")
public class Orders {

    @TableId

    private Long orderId;//主键订单ID

    private Long userId;//用户ID

    private Long vehicleId;//车辆ID

    private String orderType;//订单类型 “购买” “租借”

    private int status;//订单状态

    private BigDecimal amount;//订单金额

    private BigDecimal deposit;//押金

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")                    // 表示返回时间类型
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")      // 表示接收时间类型
    private LocalDateTime startTime;//租借开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")                    // 表示返回时间类型
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")      // 表示接收时间类型
    private LocalDateTime endTime;//结束时间

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//订单创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//订单更新时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateAdmins;//订单状态更新时,哪个管理员操作的

    @TableLogic
    private int isDelete;//逻辑删除

    @TableField(exist = false)
    private String  userToken;

    @TableField(exist = false)
    private User user;//一个订单对应一个用户

    @TableField(exist = false)
    private CarsRental carsRental;//一个订单对应一台车
}
