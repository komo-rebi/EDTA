package com.edta.framework.component.catchlog;

import com.edta.framework.component.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author wangluyao
 * @date 2022/6/21 14:51
 * @description
 */
@Data
@AllArgsConstructor
public class LogEntity extends Entity {

    private String id;
    private String parentSessionId;
    private String sessionId;
    private String method;
    private List<String> paramsType;
    private Object[] params;
    private String error;
    private Object result;
    private Long startTime;
    private Long endTime;
    private Long took;
    private String stackTrace;
    private Status status;

    public LogEntity() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.sessionId = this.id;
        this.startTime = 0L;
        this.endTime = 0L;
        this.took = 0L;
        this.status = Status.unknown;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (startTime != null) {
            took = endTime - startTime;
        } else {
            took = 0L;
        }
    }

    public void success() {
        this.status = Status.success;
    }

    public void fail() {
        this.status = Status.fail;
    }

    public enum Status {
        unknown, success, fail
    }
}
