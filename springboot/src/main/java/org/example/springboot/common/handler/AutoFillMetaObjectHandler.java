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
        Object create = metaObject.getValue("createBy");
        Object update = metaObject.getValue("updateBy");
        Object value = metaObject.getValue("remark");

        LocalDateTime date = LocalDateTime.now();
        Object createBy = create != null ? create : username;
        Object updateBy = update != null ? update : username;
        Object remark = value != null ? value : "";

        metaObject.setValue("createBy", createBy);
        metaObject.setValue("createTime", date);
        metaObject.setValue("updateBy", updateBy);
        metaObject.setValue("updateTime", date);
        metaObject.setValue("remark", remark);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User user = BaseContext.getUser();
        String username = user == null ? "" : user.getUsername();
        Object update = metaObject.getValue("updateBy");

        LocalDateTime date = LocalDateTime.now();
        Object updateBy = update != null ? update : username;

        metaObject.setValue("updateBy", updateBy);
        metaObject.setValue("updateTime", date);
    }
}
