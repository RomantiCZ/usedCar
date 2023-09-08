package cn.ikun.carshop.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("history_records")
public class HistoryRecords {

    @TableId
    public Long recordId;//历史记录ID

    private Long userId;//用户的ID

    private Long ordersId;//记录的ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更改时间

    @TableLogic
    private int isDelete;//逻辑删除

    @TableField(exist = false)
    private List<Orders> ordersList;//记录的字段

}
