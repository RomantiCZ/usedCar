package cn.ikun.carshop.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User {
    @TableId
    private Long userId; //用户ID

    @TableField(value ="username")
    private String userName;//用户名

    private String password;//密码

    private String name;//名称

    private String sex;//性别

    private String idCard;//身份证信息

    private String tel;//电话号码

    private String email;//电子邮箱

    private String img;//头像

    @TableField(fill = FieldFill.INSERT)
    private Integer status;//用户状态


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间

    @TableLogic
    private Integer isDelete;//逻辑删除

    @TableField(exist = false)
    private String userToken;//用户Token验证
}
