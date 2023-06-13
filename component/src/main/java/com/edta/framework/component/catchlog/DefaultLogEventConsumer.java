package com.edta.framework.component.catchlog;

import com.edta.framework.component.event.EventSubscriber;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangluyao
 * @date 2022/6/21 16:08
 * @description
 */
@Slf4j
public class DefaultLogEventConsumer implements EventSubscriber<LogEntity> {

    @Override
    public void handle(LogEntity logEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("sessionId: ").append(logEntity.getSessionId()).append("\n")
                .append("parentSessionId: ").append(logEntity.getParentSessionId()).append("\n")
                .append("method: ").append(logEntity.getMethod()).append("\n")
                .append("took: ").append(logEntity.getTook()).append("\n")
                .append("status: ").append(logEntity.getStatus()).append("\n")
                .append("stackTrace: ").append(logEntity.getStackTrace()).append("\n")
        ;
        log.debug(stringBuilder.toString());
    }
}
