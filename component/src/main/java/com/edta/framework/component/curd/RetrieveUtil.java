package com.edta.framework.component.curd;

import com.edta.framework.component.dto.PageResult;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangluyao
 * @date 2022/7/29 14:16
 * @description
 */
@Slf4j
public class RetrieveUtil {

    public static Map<String, Retrieve> retrieveMap = new HashMap<>();

    public static void register(String xml) {
        Retrieve retrieve = new Retrieve(xml);
        retrieveMap.put(retrieve.getName(), retrieve);
    }

    public static <Q extends JsonQuery, R, DO> PageResult<R> exec(String bizId, Q query) throws Throwable {
        if (!retrieveMap.containsKey(bizId)) {
            log.warn("缺少[{}]类型retrieve", bizId);
        }
        Retrieve<Q, R, DO> retrieve = retrieveMap.get(bizId);
        PageResult<R> pageResult;
        try {
            retrieve.before(query);
            PageResult<DO> dataObjectResult = retrieve.exec(query);
            pageResult = retrieve.convert(dataObjectResult);
            retrieve.afterReturning(pageResult);
            return pageResult;
        } catch (Throwable e) {
            retrieve.afterThrowing(query, e);
            throw e;
        } finally {
            retrieve.after(query);
        }
    }
}
