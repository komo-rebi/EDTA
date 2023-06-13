package com.edta.framework.component.util;

import com.alibaba.fastjson.JSON;
import com.edta.framework.component.domain.Identity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/03/20 15:13:56
 */
public class ConvertUtil {

    public static <T> Map<String, Object> bean2map(T t) {
        return JSON.parseObject(JSON.toJSONString(t));
    }

    public static <T> T map2bean(Map<String, Object> map, Class<T> tClass) {
        return JSON.parseObject(JSON.toJSONString(map), tClass);
    }

    public static <T, Id extends Identity<T>> List<T> unpack(Collection<Id> list) {
        return list.stream().map(Identity::id).collect(Collectors.toList());
    }

    public static <T, Id> List<Id> pack(Collection<T> list, Function<T, Id> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    public static <T, Id, E> List<Id> getIdCollection(Collection<E> entities,
                                                      Function<E, Id> function) {
        return entities.stream().map(function).collect(Collectors.toList());
    }

    public static <T, K> Map<K, T> collectToMap(Collection<T> collection, Function<T, K> function) {
        Map<K, T> map = new HashMap<>(collection.size());
        collection.forEach(c -> map.put(function.apply(c), c));
        return map;
    }

    public static <T, K> Map<K, List<T>> collectionToListMap(Collection<T> collection, Function<T, K> function) {
        Map<K, List<T>> map = new HashMap<>(collection.size());
        collection.forEach(c -> {
            List<T> list = mapPutIfAbsent(map, function.apply(c), new ArrayList<>());
            list.add(c);
        });
        return map;
    }

    public static <K, V> V mapPutIfAbsent(Map<K, V> map, K key, V value) {
        V v = map.get(key);
        if (v == null) {
            v = map.put(key, value);
            v = value;
        }
        return v;
    }
}
