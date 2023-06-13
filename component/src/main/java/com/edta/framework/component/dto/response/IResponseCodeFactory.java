package com.edta.framework.component.dto.response;

/**
 * @author wly
 * @date 2020/12/30 10:26:20
 */
public interface IResponseCodeFactory {

    /**
     * 获取成功码
     *
     * @return
     */
    ResponseCode getSuccessResponseCode();

    /**
     * 获取参数错误码
     *
     * @return
     */
    ResponseCode getBadParamResponseCode();
}
