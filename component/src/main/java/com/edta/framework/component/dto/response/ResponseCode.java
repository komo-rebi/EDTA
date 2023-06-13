package com.edta.framework.component.dto.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author wly
 * @date 2020/12/30 11:08:54
 */
@Getter
@ToString
public class ResponseCode {

    private final String code;
    private final String message;

    public ResponseCode(Integer code, String message) {
        this(code + "", message);
    }

    public ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
