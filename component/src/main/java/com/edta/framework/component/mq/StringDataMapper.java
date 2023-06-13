package com.edta.framework.component.mq;

import com.alibaba.fastjson.JSON;

/**
 * @author wangluyao
 * @date 2022/6/23 11:37
 * @description
 */
public class StringDataMapper implements DataMapper<String> {
    @Override
    public <T> String entityToValue(T t) {
        return JSON.toJSONString(t);
    }

    @Override
    public <T> T valueToEntity(String value, Class<T> data) {
        return JSON.parseObject(value, data);
    }

}
