package cn.ikun.carshop.utils;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MessageUtil<T> {
    private Integer code; //200表示成功 500表示代码错误 400表示失败 404表示没有

    private String msg; //信息

    private T data; //数据信息

    private Map map = new HashMap(); //动态数据

    private List<T> list =new ArrayList<>();//动态数组数据

    public static <T> MessageUtil<T> success(T object){
        MessageUtil<T> utils = new MessageUtil<>();
        utils.data = object;
        utils.code = 200;
        return utils;
    }

    public static <T> MessageUtil<T> successForMsg(T object,String msg){
        MessageUtil<T> utils = new MessageUtil<>();
        utils.data = object;
        utils.msg = msg;
        utils.code = 200;
        return utils;
    }

    //查找失败
    public static <T> MessageUtil<T> error(String msg){
        MessageUtil<T> utils = new MessageUtil<>();
        utils.code = 400;
        utils.msg = msg;
        return  utils;
    }

}
