package cn.ikun.carshop.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Email {

    private String to;//发送到哪里

    private String subject;//发送主题

    private String text;//发送内容

    private String name;//用户名称

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")                    // 表示返回时间类型
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")      // 表示接收时间类型
    private LocalDateTime startTime;//租借时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")                    // 表示返回时间类型

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")      // 表示接收时间类型
    private LocalDateTime endTime;//租借时间

    public Email(String to, String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.to = to;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String setRentSubject(){
        return "华晨二手：恭喜用户:"+this.name+" 租借成功";
    }

    public String setRentalText(){
        return "贵宾租车时间为："+this.startTime+"到"+this.endTime+" 请贵宾于"+this.startTime+"前领取车辆";
    }


    public String setSaleSubject(){
        return "华晨二手：恭喜用户:"+this.name+" 购买成功";
    }

    public String setSaleText(){
        return "贵宾购买时间为："+this.startTime+" 请贵宾于"+this.startTime+" 查看车辆";
    }



}

