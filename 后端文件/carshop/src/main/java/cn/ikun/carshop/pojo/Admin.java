package cn.ikun.carshop.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@TableName("admins")
public class Admin {

    @TableId
    private Long adminId; //主键

    private String username;//管理员账号

    private String password;//管理员密码

    private String name;//管理员姓名

    private String sex;//性别

    private String tel;//管理员联系方式

    private String idCard;//管理员证件号码

    private String email;//电子邮箱

    @TableField(fill = FieldFill.INSERT,value = "role")
    private String role;//分为普通管理员和超级管理员

    @TableField(fill = FieldFill.INSERT)
    private int status;//管理员的状态0正常 1禁用

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateAdmins;//更改时管理员的id

    @TableLogic
    private Integer isDelete;//逻辑删除

    @TableField(exist = false)
    private String adminToken;//验证token信息

}
