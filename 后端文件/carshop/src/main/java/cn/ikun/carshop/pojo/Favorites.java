package cn.ikun.carshop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@TableName("favorites")
public class Favorites {
    @TableId
    private Long favoriteId;//主键

    private Long userId;//用户ID

    private Long vehicleId;//车量ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间

    @TableLogic
    private int isDelete;//逻辑删除

    @TableField(exist = false)
    private List<CarsRental> carsRentalList;//租借车类型喜欢的集合

    @TableField(exist = false)
    private List<CarsSale> carsSaleList;//购买类型的车量的合集
}
