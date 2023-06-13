package com.edta.framework.component.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Set;

/**
 * @author wangluyao
 * @date 2022/6/27 13:35
 * @description
 */
public class JsonUtil {

    public static JSONObject changeJsonObjectKey(JSONObject sourceJsonObject, boolean changeMode) {
        JSONObject object = new JSONObject();
        Set<String> keySet = sourceJsonObject.keySet();
        Iterator<String> keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            String jsonKey = keyIterator.next();
            Object valueObject = sourceJsonObject.get(jsonKey);
            jsonKey = changeString(jsonKey, changeMode);
            if (valueObject instanceof JSONObject) {
                valueObject = changeJsonObjectKey((JSONObject) valueObject, changeMode);
            } else if (valueObject instanceof JSONArray) {
                valueObject = changeJsonArrayKey((JSONArray) valueObject, changeMode);
            }
            object.put(jsonKey, valueObject);
        }
        return object;
    }

    public static JSONArray changeJsonArrayKey(JSONArray sourceJsonArray, boolean changeMode) {
        JSONArray jsonArray = new JSONArray();
        for (Object object : sourceJsonArray) {
            if (object instanceof JSONObject) {
                object = changeJsonObjectKey((JSONObject) object, changeMode);
            } else if (object instanceof JSONArray) {
                object = changeJsonArrayKey((JSONArray) object, changeMode);
            }
            jsonArray.add(object);
        }
        return jsonArray;
    }

    private static String changeString(String input, boolean changeMode) {
        if (changeMode) {
            return input.toUpperCase();
        } else {
            return input.toLowerCase();
        }
    }

    public static void main(String[] args) {
        String json = "{\"MSG\":{\"VERSION\":\"2\",\"LICENSE\":\"test_license\",\"TEST\":[{\"BEGIN\":\"2012-08-21 21:05:06\",\"END\":\"2023-09-15 20:05:06\"},{\"BEGIN\":\"2012-08-21 21:05:06\",\"END\":\"2023-09-15 20:05:06\"}],\"TTL\":{\"BEGIN\":\"2012-08-21 21:05:06\",\"END\":\"2023-09-15 20:05:06\"},\"SERVICE\":\"TUP5\",\"FILTER\":{\"group_id\":\"0\",\"src_ip\":\"10.0.2.100\",\"src_ip_mask\":\"21\",\"dst_ip\":\"10.0.2.200\",\"dst_ip_mask\":\"22\",\"src_port\":\"XX700\",\"src_port_mask\":\"3\",\"dst_port\":\"XX701\",\"dst_port_mask\":\"5\",\"protocol\":\"UDP\",\"dir\":\"BOTH\",\"istest\":\"0\",\"result\":\"0\",\"balance_group_num\":\"64\",\"balance_group_id\":\"1\"},\"PRIORITY\":\"high\"}}";
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject destObject = changeJsonObjectKey(jsonObject, false);
        System.out.println(JSON.toJSONString(destObject));
    }
}
