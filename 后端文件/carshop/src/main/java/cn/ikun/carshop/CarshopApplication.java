package cn.ikun.carshop;

import cn.ikun.carshop.utils.JwtConfigUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.ikun.carshop.mapper")
@EnableTransactionManagement //开启事物注解的
@EnableConfigurationProperties({JwtConfigUtils.class}) //配置spring——properties
@EnableAsync //异步操作
@EnableScheduling //开启定时任务
public class CarshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarshopApplication.class, args);
    }

}
