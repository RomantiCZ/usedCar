package cn.ikun.carshop.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元素数据处理器
 */

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作、自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("公共填充字段[insertFill]");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

        if (metaObject.hasGetter("role")) {
            metaObject.setValue("role","普通管理员");
        }
        metaObject.setValue("status",0);
    }

    /**
     * 更新操作、自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共填充字段[updateFill]");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
