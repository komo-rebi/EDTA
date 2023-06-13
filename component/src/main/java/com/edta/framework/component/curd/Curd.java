package com.edta.framework.component.curd;

import com.edta.framework.component.page.PageData;
import com.edta.framework.component.queryhelper.DefaultQuery;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/7/28 18:04
 * @description
 */
public interface Curd {

    <T> void save(T t, Class<T> tClass);

    <T> void saveAll(List<T> tList, Class<T> tClass);

    <T, Q extends DefaultQuery> PageData<T> list(Q query, Class<T> tClass);

    <T, Q extends DefaultQuery> long count(T query, Class<T> tClass);
}
