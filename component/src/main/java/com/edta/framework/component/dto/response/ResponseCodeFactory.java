package com.edta.framework.component.dto.response;

/**
 * 客户端可以实现自己的ResponseCodeFactory来替换本工厂类
 *
 * @author wly
 * @date 2021/01/13 17:02:32
 */
public class ResponseCodeFactory implements IResponseCodeFactory {

    @Override
    public ResponseCode getSuccessResponseCode() {
        return new ResponseCode("0", "Success");
    }

    @Override
    public ResponseCode getBadParamResponseCode() {
        return new ResponseCode("-1", "Bad request parameter");
    }
}
