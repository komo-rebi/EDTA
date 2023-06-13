package com.edta.framework.component.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wly
 * @date 2021/03/29 16:45:34
 */
@Slf4j
@ConditionalOnClass(value = {MybatisConfiguration.class})
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

    public MybatisPlusConfig() {
        log.debug("Load");
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "deleted", Long.class, 0L);
        this.strictInsertFill(metaObject, "createTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
