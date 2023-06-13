package com.edta.framework.component.mq;

/**
 * @author wangluyao
 * @date 2022/6/23 10:22
 * @description
 */
public interface Mq {

    <T> void send(String queue, T data);

    <T> T recv(String queue, Class<T> tClass);
}
