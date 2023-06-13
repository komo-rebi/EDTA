package com.edta.framework.component.mq;

import com.edta.framework.component.common.CallBack;
import com.edta.framework.component.exception.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wangluyao
 * @date 2022/6/23 11:34
 * @description
 */
public class AbstractRedisMq implements RedisMq {

    private final StringRedisTemplate redisTemplate;
    private final StringDataMapper dataMapper;

    public AbstractRedisMq(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.dataMapper = new StringDataMapper();
    }

    public Long size(String queue) {
        Assert.notBlank(queue);
        return redisTemplate.opsForList().size(queue);
    }

    @Override
    public <T> void send(String queue, T data, Long timeout, TimeUnit timeUnit) {
        Assert.notBlank(queue);
        Assert.notNull(data);

        redisTemplate.opsForList().rightPush(queue, dataMapper.entityToValue(data));
        if (timeout > 0L) {
            redisTemplate.expire(queue, timeout, timeUnit);
        }
    }

    @Override
    public <T, R> T recv(String queue, Class<T> tClass, CallBack<T, R> callBack) {
        Assert.notBlank(queue);
        Long size = redisTemplate.opsForList().size(queue);
        if (size == null || size <= 0) {
            return null;
        }
        List<String> messages = redisTemplate.opsForList().range(queue, 0, 0);
        String message = null;
        if (CollectionUtils.isNotEmpty(messages)) {
            message = messages.get(0);
        }
        T data = dataMapper.valueToEntity(message, tClass);
        if (callBack != null) {
            try {
                callBack.handle(data);
            } catch (Exception e) {
                if (StringUtils.isNotBlank(message)) {
                    // 将出现异常的内容，放到最后
                    redisTemplate.opsForList().rightPush(queue, message);
                    redisTemplate.opsForList().leftPop(queue);
                }
                throw e;
            }
        }
        redisTemplate.opsForList().leftPop(queue);
        return data;
    }

    @Override
    public <T> void bulkSend(String queue, List<T> dataList, Long timeout, TimeUnit timeUnit) {
        dataList.forEach(data -> {
            send(queue, data, timeout, timeUnit);
        });
    }

    @Override
    public <T, R> int bulkRecv(String queue, Class<T> tClass, Integer batchSize, BulkCallBack<T, R> callBack) {
        Assert.notBlank(queue);
        Long size = redisTemplate.opsForList().size(queue);
        if (size == null || size <= 0) {
            return 0;
        }
        List<String> messages = redisTemplate.opsForList().range(queue, 0, Math.min(batchSize, size) - 1);
        if (CollectionUtils.isEmpty(messages)) {
            return 0;
        }
        List<T> dataList = messages.stream().map(message -> dataMapper.valueToEntity(message, tClass)).collect(Collectors.toList());
        callBack.handle(dataList);
        redisTemplate.opsForList().leftPop(queue);
        return dataList.size();
    }
}
