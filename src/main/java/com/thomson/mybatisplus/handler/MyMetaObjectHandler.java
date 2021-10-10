package com.thomson.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("start insert fill ...");
        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"deleted",Integer.class,0);

        // 避免自动填充时开销过大，填充前先判断当前对象中是否有相关属性
        boolean hasAuthor = metaObject.hasSetter("author");
        if(hasAuthor){
            log.info("start insert fill author ...");
            this.strictInsertFill(metaObject,"author",String.class,"Thomson");
        }

        // 判断age是否已赋值
        Object age = this.getFieldValByName("userAge", metaObject);
        if (age == null){
            log.info("start insert fill userAge ...");
            this.strictInsertFill(metaObject,"userAge", Integer.class, 18);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ...");
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
