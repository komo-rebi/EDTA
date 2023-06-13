package com.edta.framework.component.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wangluyao
 * @date 2022/7/29 16:53
 * @description
 */
public class XmlUtil {

    public static <T> T xmlToBean(String xml, String path, Class<T> tClass) {
        Map<String, Object> xmlMap = cn.hutool.core.util.XmlUtil.xmlToMap(xml);
        String json = JSON.toJSONString(xmlMap);
        Object object = JSONPath.extract(json, "$." + path);
        if (List.class.isAssignableFrom(tClass)) {
            if (object instanceof JSONObject) {
                return (T) Arrays.asList(object);
            }
        }
        return JSON.parseObject(JSON.toJSONString(object), tClass);
    }
//
//    public static void main(String[] args) {
//        File file = new File("C:\\Users\\wly32\\Documents\\Work\\Cloud\\cloud_test\\backend\\trunk\\V1.1.0\\component\\src\\main\\resources\\biz-demo.xml");
//        String xml = FileUtil.readString(file, StandardCharsets.UTF_8);
//        List list = xmlToBean(xml, "fields.field", List.class);
//        System.out.println(list);
//    }
}
