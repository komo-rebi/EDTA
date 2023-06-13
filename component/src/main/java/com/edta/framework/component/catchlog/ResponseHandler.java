package com.edta.framework.component.catchlog;

import com.edta.framework.component.dto.PageResponse;
import com.edta.framework.component.dto.Response;
import com.edta.framework.component.dto.SingleResponse;
import com.edta.framework.component.dto.response.ResponseCode;
import com.edta.framework.component.exception.BadParamException;
import com.edta.framework.component.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wly
 * @date 2021/01/19 11:23:30
 */
@Slf4j
public class ResponseHandler {

    public static Object handle(Class returnType, String code, String message, Throwable e) throws Throwable {
        if (isFrameResponse(returnType)) {
            return handleFrameResponse(returnType, code, message);
        }
        throw e;
    }

    public static Object handle(Class returnType, BaseException e) throws Throwable {
        return handle(returnType, e.getCode(), e.getMessage(), e);
    }

    public static Object handle(Class returnType, BadParamException e) throws Throwable {
        return handle(returnType, e.getResponseCode().getCode(), e.getResponseCode().getMessage(), e);
    }

    private static boolean isFrameResponse(Class returnType) {
        return returnType == Response.class || returnType.getGenericSuperclass() == Response.class;
    }

    private static Object handleFrameResponse(Class returnType, String code, String message) {
        try {
            if (returnType == SingleResponse.class) {
                return SingleResponse.build(new ResponseCode(code, message));
            } else if (returnType == PageResponse.class) {
                return PageResponse.build(new ResponseCode(code, message));
            }
            return SingleResponse.build(new ResponseCode(code, message));
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return null;
        }
    }
}
