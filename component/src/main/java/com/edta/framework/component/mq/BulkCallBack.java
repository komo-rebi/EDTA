package com.edta.framework.component.mq;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/7/21 16:46
 * @description
 */
@FunctionalInterface
public interface BulkCallBack<T, R> {
    R handle(List<T> list);
}
