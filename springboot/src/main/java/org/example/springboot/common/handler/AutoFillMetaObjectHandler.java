package org.example.springboot.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.example.springboot.common.BaseContext;
import org.example.springboot.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 */
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        User user = BaseContext.getUser();
        String username = user == null ? "" : user.getUsername();
        LocalDateTime date = LocalDateTime.now();
        metaObject.setValue("createBy", metaObject.getValue("createBy") != null ? metaObject.getValue("createBy") : username);
        metaObject.setValue("createTime", date);
        metaObject.setValue("updateBy", metaObject.getValue("updateBy") != null ? metaObject.getValue("updateBy") : username);
        metaObject.setValue("updateTime", date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User user = BaseContext.getUser();
        String username = user == null ? "" : user.getUsername();
        LocalDateTime date = LocalDateTime.now();
        metaObject.setValue("updateBy", metaObject.getValue("updateBy") != null ? metaObject.getValue("updateBy") : username);
        metaObject.setValue("updateTime", date);
    }
}
