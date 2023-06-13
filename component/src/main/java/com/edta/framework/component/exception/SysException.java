package com.edta.framework.component.exception;

/**
 * 系统异常，无需重试
 *
 * @author wly
 * @date 2020/12/22 22:38:59
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SysException(String message) {
        super("System error", message);
    }

    public SysException(String message, Throwable e) {
        super("System error", message, e);
    }

    public SysException(String code, String message) {
        super(code, message);
    }

    public SysException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
