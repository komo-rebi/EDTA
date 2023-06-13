package com.edta.framework.component.exception;

import com.edta.framework.component.dto.response.IResponseCodeFactory;
import com.edta.framework.component.dto.response.ResponseCode;
import com.edta.framework.component.dto.response.ResponseCodeFactory;
import com.edta.framework.component.util.ApplicationContextHelper;

/**
 * 统一参数错误异常
 *
 * @author wly
 * @date 2021/01/19 14:23:11
 */
public class BadParamException extends BizException {

    private final ResponseCode responseCode;

    public BadParamException() {
        super("Bad request parameter");
        responseCode = getBadParamResponseCode();
    }

    public BadParamException(String message) {
        super(message);
        responseCode = getBadParamResponseCode();
    }

    public BadParamException(String code, String message) {
        super(code, message);
        responseCode = getBadParamResponseCode();
    }

    public BadParamException(String code, String message, Throwable e) {
        super(code, message, e);
        responseCode = getBadParamResponseCode();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    private ResponseCode getBadParamResponseCode() {
        try {
            IResponseCodeFactory responseCodeFactory = ApplicationContextHelper.getBean(IResponseCodeFactory.class);
            return responseCodeFactory.getBadParamResponseCode();
        } catch (Throwable e) {
            ResponseCodeFactory responseCodeFactory = new ResponseCodeFactory();
            return responseCodeFactory.getBadParamResponseCode();
        }
    }
}
