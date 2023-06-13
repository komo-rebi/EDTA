package com.edta.framework.component.dto;

import java.util.LinkedHashMap;

/**
 * @author wly
 * @date 2020/12/23 16:44:50
 */
public class ModelMap extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1;

    public ModelMap addAttribute(String key, Object value) {
        put(key, value);
        return this;
    }

    public Object getAttribute(String key) {
        return get(key);
    }
}
