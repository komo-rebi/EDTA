package com.edta.framework.component.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wly
 * @date 2021/01/13 16:44:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private final String code;

    public BaseException(String message) {
        super(message);
        this.code = "";
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
        this.code = "";
    }

    public BaseException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}
