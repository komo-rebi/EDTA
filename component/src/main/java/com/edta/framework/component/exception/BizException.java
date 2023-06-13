package com.edta.framework.component.exception;

/**
 * 业务异常
 *
 * @author wly
 * @date 2021/01/11 10:22:29
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException(String message) {
        super("Business error", message);
    }

    public BizException(Integer code, String message) {
        super(code + "", message);
    }

    public BizException(String code, String message) {
        super(code, message);
    }

    public BizException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
