package cn.ikun.carshop.common;


import cn.ikun.carshop.utils.MessageUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Configuration
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(MybatisPlusException.class)
    @ResponseBody
    public MessageUtil<String> sqlerror(MybatisPlusException mp){
        log.info("发生了错误：{}",mp);
        return MessageUtil.error("数据修改发生时未知错误");
    }

    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    public MessageUtil<String> mybatisError(){

        return MessageUtil.error("数据修改时发生未知错误,请联系管理员");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public MessageUtil<String> othererror(RuntimeException e){
        log.info("发生错误：{}",e);
        return MessageUtil.error("服务器发生未知错误");
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public MessageUtil<String> ioException(IOException e){
        log.info("发生错误：{}",e);
        return MessageUtil.error("服务器发生未知错误");
    }
//    @ExceptionHandler()
}
