package com.edta.framework.component.curd;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.edta.framework.component.queryhelper.AbstractQueryField;
import com.edta.framework.component.queryhelper.EqQueryField;
import com.edta.framework.component.queryhelper.InQueryField;
import com.edta.framework.component.util.ConvertUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wangluyao
 * @date 2022/7/28 18:02
 * @description
 */
@Slf4j
public class CurdHelper {

    private final Map<String, Engine> engineMap;

    public CurdHelper(String xmlFilePath) {
        this(new File(xmlFilePath));
    }

    public CurdHelper(File xmlFile) {
        String xml = FileUtil.readString(xmlFile, StandardCharsets.UTF_8);
        JSONObject json = JSONUtil.xmlToJson(xml);
        Object field = JSONUtil.getByPath(json, "fields.field");
        List<Engine> engines = new ArrayList<>();
        if (field instanceof JSONObject) {
            Engine engine = JSON.parseObject(JSON.toJSONString(field), Engine.class);
            engines.add(engine);
        } else if (field instanceof JSONArray) {
            List<Engine> engine = JSON.parseArray(JSON.toJSONString(field), Engine.class);
            engines.addAll(engine);
        }
        // 扩展
        engines.forEach(Engine::init);
        engineMap = ConvertUtil.collectToMap(engines, Engine::getKey);
    }

    public <T> List<AbstractQueryField<?>> getQueryField(String key, Object value) {
        if (!engineMap.containsKey(key)) {
            return Collections.emptyList();
        }
        Engine engine = engineMap.get(key);
        List<AbstractQueryField<?>> queryFields = new ArrayList<>();
        engine.queryFieldClasses.forEach(queryFieldClass -> {
            try {
                Constructor<?> constructor = queryFieldClass.getConstructor(String.class, Object.class);
                queryFields.add((AbstractQueryField<?>) constructor.newInstance(engine.getActualKey(), value));
            } catch (Exception e) {
                log.error("添加QueryField失败, key: {}, value: {}", key, value, e);
            }
        });
        return queryFields;
    }

    /**
     * example:
     * key -> type: tuple5/dpi/regex/zk
     * actual -> key
     * op[] -> eq,in
     */
    @Data
    public static class Engine {
        private String key;
        private String actualKey;
        private String function;
        private List<Class<?>> queryFieldClasses;

        void init() {
            List<String> expresses = StrUtil.split(function, ",");
            queryFieldClasses = new ArrayList<>();
            for (String exp : expresses) {
                if ("eq".equalsIgnoreCase(exp)) {
                    queryFieldClasses.add(EqQueryField.class);
                } else if ("in".equalsIgnoreCase(exp)) {
                    queryFieldClasses.add(InQueryField.class);
                }
            }
        }
    }
}
