package com.edta.framework.component.mq;

import com.edta.framework.component.common.CallBack;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangluyao
 * @date 2022/6/23 10:24
 * @description
 */
public interface RedisMq extends Mq {

    @Override
    default <T> void send(String queue, T data) {
        this.send(queue, data, 0L, TimeUnit.MILLISECONDS);
    }

    @Override
    default <T> T recv(String queue, Class<T> tClass) {
        return this.recv(queue, tClass, null);
    }

    <T> void send(String queue, T data, Long timeout, TimeUnit timeUnit);

    <T, R> T recv(String queue, Class<T> tClass, CallBack<T, R> callBack);

    <T> void bulkSend(String queue, List<T> data, Long timeout, TimeUnit timeUnit);

    <T, R> int bulkRecv(String queue, Class<T> tClass, Integer batchSize, BulkCallBack<T, R> callBack);
}
