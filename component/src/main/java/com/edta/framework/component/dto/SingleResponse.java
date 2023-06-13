package com.edta.framework.component.dto;

import com.edta.framework.component.dto.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wly
 * @date 2020/12/22 22:34:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SingleResponse<T> extends Response {

    private T result;

    SingleResponse(T result) {
        this.result = result;
    }

    public static <T> SingleResponse<T> build(ResponseCode responseCode, T result) {
        return new SingleResponse<>(result).setResponseCode(responseCode);
    }

    public static <T> SingleResponse<T> buildSuccess(T result) {
        return new SingleResponse<>(result);
    }

    public static <T> SingleResponse<T> build(ResponseCode responseCode) {
        return (SingleResponse<T>) build(responseCode, new ModelMap());
    }

    public static <T> SingleResponse<T> buildSuccess() {
        return (SingleResponse<T>) buildSuccess(new ModelMap());
    }
}
